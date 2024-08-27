package ifba.Olimpiada.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ifba.Olimpiada.dtos.AssociacaoUsuarioPaisDto;
import ifba.Olimpiada.dtos.DadosAutenticacao;
import ifba.Olimpiada.dtos.DadosTokenJWT;
import ifba.Olimpiada.dtos.PaisDto;
import ifba.Olimpiada.dtos.UsuarioDto;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.models.Role;
import ifba.Olimpiada.models.Usuario;
import ifba.Olimpiada.repositories.UsuarioRepository;
import ifba.Olimpiada.services.AssociacaoService;
import ifba.Olimpiada.services.JWTokenService;
import ifba.Olimpiada.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private JWTokenService tokenService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AssociacaoService associacaoService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
    @PostMapping("/register")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody DadosAutenticacao dados) {
        Usuario usuario = usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/associar-pais")
    public ResponseEntity<String> associarUsuarioComPais(@RequestBody AssociacaoUsuarioPaisDto associacaoDto) {
        associacaoService.associarUsuarioComPais(associacaoDto.usuarioId(), associacaoDto.paisId());
        return ResponseEntity.ok("Usuário associado ao país com sucesso!");
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioDto> obterUsuarioAtual() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        UsuarioDto usuarioDto = new UsuarioDto(usuario);
        return ResponseEntity.ok(usuarioDto);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> obterRolesDoUsuario() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<Role> roles = usuario.getRoles();
        return ResponseEntity.ok(roles);
    }
   
    @GetMapping("/{id}/paises-seguidos")
    public List<Long> getPaisesSeguidos(@PathVariable Long id) {
        List<Pais> paisesSeguidos = usuarioService.getPaisesSeguidos(id);
        return paisesSeguidos.stream().map(Pais::getId).collect(Collectors.toList());
    }
}
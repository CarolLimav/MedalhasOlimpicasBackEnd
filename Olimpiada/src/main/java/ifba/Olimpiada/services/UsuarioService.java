package ifba.Olimpiada.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import ifba.Olimpiada.dtos.DadosAutenticacao;
import ifba.Olimpiada.dtos.UsuarioDto;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.models.Usuario;
import ifba.Olimpiada.repositories.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	
	public Page<UsuarioDto> listar(Pageable pageble) {
		
		Page<Usuario> usuarios = usuarioRepository.findAll(pageble);
		return usuarios.map(UsuarioDto::new);
	}
	
	public Usuario cadastrarUsuario(DadosAutenticacao dados) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dados.login());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        
        return usuarioRepository.save(usuario);
    }
	
	
	public ResponseEntity<UsuarioDto> salvar(UsuarioDto usuarioDto) {

		Usuario usuario = new Usuario(usuarioDto);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}
	
	public ResponseEntity<UsuarioDto> atualizar( UsuarioDto usuarioDto,  Long id) {
		var usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			Usuario usuarioAtualizado = usuario.get();
			usuarioAtualizado.setNome(usuarioDto.nome());
			usuarioAtualizado.setLogin(usuarioDto.login());
			usuarioAtualizado.setSenha(usuarioDto.senha());
			usuarioRepository.save(usuarioAtualizado);
			return ResponseEntity.ok(new UsuarioDto(usuarioAtualizado));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<UsuarioDto> deletar(Long id) {
		var usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	public List<Pais> getPaisesSeguidos(Long usuarioId) {
		 Usuario usuario = usuarioRepository.findById(usuarioId)
		            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		        return usuario.getPaisesSeguidos();
		}
}

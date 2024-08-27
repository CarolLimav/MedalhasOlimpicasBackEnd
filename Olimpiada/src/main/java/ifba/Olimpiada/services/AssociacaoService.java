package ifba.Olimpiada.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifba.Olimpiada.dtos.CriarMedalhaDto;
import ifba.Olimpiada.dtos.PaisEmailDto;
import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.models.Usuario;
import ifba.Olimpiada.repositories.EsporteRepository;
import ifba.Olimpiada.repositories.PaisRepository;
import ifba.Olimpiada.repositories.UsuarioRepository;

//@Service
//public class AssociacaoService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private PaisRepository paisRepository;
//    
//    @Autowired
//    private EsporteRepository esporteRepository;
//
//    public void associarUsuarioComPais(Long usuarioId, Long paisId) {
//        Usuario usuario = usuarioRepository.findById(usuarioId)
//            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
//        Pais pais = paisRepository.findById(paisId)
//            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));
//        
//        usuario.getPaisesSeguidos().add(pais);
//        pais.getSeguidores().add(usuario);
//
//        usuarioRepository.save(usuario);
//    }
//    
//    public List<Usuario> obterUsuariosPorPais(Long paisId) {
//        Pais pais = paisRepository.findById(paisId)
//            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));
//
//        return pais.getSeguidores();
//    }
//    
//    public PaisEmailDto obterPaisComEmails(CriarMedalhaDto medalhaDto) {
//        Pais pais = paisRepository.findById(medalhaDto.paisId())
//            .orElseThrow(() -> new RuntimeException("País não encontrado"));
//
//        
//        List<String> emails = pais.getSeguidores().stream()
//            .map(usuario -> usuario.getEmail()) 
//            .toList();
//        
//       Esporte esporte = esporteRepository.findById(medalhaDto.esporteId()).orElseThrow(() -> new RuntimeException("Esporte não encontrado"));;
//        
//        return new PaisEmailDto(pais.getNome(), emails,  medalhaDto.nomeAtleta(), medalhaDto.descricao(), esporte.getNome(), medalhaDto.tipoMedalha());
//    }
//    
//}
@Service
public class AssociacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PaisRepository paisRepository;
    
    @Autowired
    private EsporteRepository esporteRepository;

    public void associarUsuarioComPais(Long usuarioId, Long paisId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Pais pais = paisRepository.findById(paisId)
            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));
        
        usuario.getPaisesSeguidos().add(pais);
        pais.getSeguidores().add(usuario);
//        usuario.getPais().add(pais);
//        pais.getUsuarios().add(usuario);

        usuarioRepository.save(usuario);
    }
    
    public List<Usuario> obterUsuariosPorPais(Long paisId) {
        Pais pais = paisRepository.findById(paisId)
            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        return pais.getSeguidores();
    }
    
    public PaisEmailDto obterPaisComEmails(CriarMedalhaDto medalhaDto) {
        Pais pais = paisRepository.findById(medalhaDto.paisId())
            .orElseThrow(() -> new RuntimeException("País não encontrado"));

        
        List<String> emails = pais.getSeguidores().stream()
            .map(usuario -> usuario.getEmail()) //
            .toList();
        
       Esporte esporte = esporteRepository.findById(medalhaDto.esporteId()).orElseThrow(() -> new RuntimeException("Esporte não encontrado"));;
        
        return new PaisEmailDto(pais.getNome(), emails,  medalhaDto.nomeAtleta(), medalhaDto.descricao(), esporte.getNome(), medalhaDto.tipoMedalha());
    }
}
package ifba.Olimpiada.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ifba.Olimpiada.dtos.CriarMedalhaDto;
import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.dtos.PaisDto;
import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.models.Medalha;
import ifba.Olimpiada.repositories.EsporteRepository;
import ifba.Olimpiada.repositories.MedalhaRepository;
import ifba.Olimpiada.repositories.PaisRepository;
import jakarta.transaction.Transactional;

@Service
public class MedalhaService {

	@Autowired
	private MedalhaRepository medalhaRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private EsporteRepository esporteRepository;
	
	public List<MedalhaDto> listarTodos() {
		return medalhaRepository.findAll().stream().map(MedalhaDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<MedalhaDto> cadastrar(CriarMedalhaDto criarmedalhaDto) {
		
		var esporteDto = new EsporteDto(esporteRepository.findById(criarmedalhaDto.esporteId()).get());
		var paisDto = new PaisDto(paisRepository.findById(criarmedalhaDto.paisId()).get());
		var medalhaDto = new MedalhaDto(null, criarmedalhaDto.nomeAtleta(), criarmedalhaDto.descricao(), paisDto, esporteDto, criarmedalhaDto.tipoMedalha());
		
		return ResponseEntity.ok(new MedalhaDto(medalhaRepository.save(new Medalha(medalhaDto))));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MedalhaDto> atualizar(@RequestBody  MedalhaDto medalhaDto, @PathVariable Long id) {

		var op = medalhaRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		medalhaRepository.save(op.get());
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<?> DeletarPorId(Long id) {
		var op = medalhaRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		medalhaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}

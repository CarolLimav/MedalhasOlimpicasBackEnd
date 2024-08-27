package ifba.Olimpiada.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.repositories.EsporteRepository;
import jakarta.transaction.Transactional;

@Service
public class EsporteService {

	@Autowired
	private EsporteRepository esporteRepository;
	
	public List<EsporteDto> listarTodos() {
		return esporteRepository.findAll().stream().map(EsporteDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<EsporteDto> cadastrar(EsporteDto esporteDto) {
		return ResponseEntity.ok(new EsporteDto(esporteRepository.save(new Esporte(esporteDto))));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EsporteDto> atualizar(@RequestBody  EsporteDto esporteDto, @PathVariable Long id) {

		var op = esporteRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		esporteRepository.save(op.get());
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<?> DeletarPorId(Long id) {
		var op = esporteRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		esporteRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}

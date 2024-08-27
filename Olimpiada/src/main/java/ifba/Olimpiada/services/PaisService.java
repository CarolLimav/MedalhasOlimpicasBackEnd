package ifba.Olimpiada.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.dtos.PaisDto;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.repositories.PaisRepository;
import jakarta.transaction.Transactional;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;
	
	public List<PaisDto> listarTodos() {
		return paisRepository.findAll().stream().map(PaisDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<PaisDto> cadastrar(PaisDto paisDto) {
		return ResponseEntity.ok(new PaisDto(paisRepository.save(new Pais(paisDto))));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaisDto> atualizar(@RequestBody  PaisDto paisDto, @PathVariable Long id) {

		var op = paisRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		paisRepository.save(op.get());
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<?> DeletarPorId(Long id) {
		var op = paisRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		paisRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	 public ResponseEntity<PaisDto> listarPorId(Long id) {
	        var op = paisRepository.findById(id);
	        if (op.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(new PaisDto(op.get()));
	    }
	
}



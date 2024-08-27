package ifba.Olimpiada.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.dtos.PaisDto;
import ifba.Olimpiada.services.MedalhaService;
import ifba.Olimpiada.services.PaisService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@Autowired
	PaisService paisService;
	
	@GetMapping
	public List<PaisDto> listar() {
			
		return paisService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<PaisDto> salvar(@RequestBody PaisDto paisDto) {

		return paisService.cadastrar(paisDto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaisDto> atualizar(@RequestBody  PaisDto paisDto, @PathVariable Long id) {

		return paisService.atualizar(paisDto, id);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		return paisService.DeletarPorId(id);
	}
}

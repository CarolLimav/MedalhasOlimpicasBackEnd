package ifba.Olimpiada.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.services.EsporteService;
import ifba.Olimpiada.services.MedalhaService;
import jakarta.transaction.Transactional;
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/esportes")
public class EsporteController {

	@Autowired
	EsporteService esporteService;
	
	@GetMapping
	public List<EsporteDto> listar() {
			
		return esporteService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<EsporteDto> salvar(@RequestBody EsporteDto esporteDto) {

		return esporteService.cadastrar(esporteDto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EsporteDto> atualizar(@RequestBody  EsporteDto esporteDto, @PathVariable Long id) {

		return esporteService.atualizar(esporteDto, id);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		return esporteService.DeletarPorId(id);
	}
}

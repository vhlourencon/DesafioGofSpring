package one.digitalinnovation.gof.controller;

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

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Filme;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.FilmeService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author falvojr
 */
@RestController
@RequestMapping("filmes")
public class FilmeRestController {

	@Autowired
	private FilmeService filmeService;

	@GetMapping
	public ResponseEntity<Iterable<Filme>> buscarTodos() {
		return ResponseEntity.ok(filmeService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(filmeService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Filme> inserir(@RequestBody Filme filme) {
		filmeService.inserir(filme);
		return ResponseEntity.ok(filme);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Filme> atualizar(@PathVariable Long id, @RequestBody Filme filme) {
		filmeService.atualizar(id, filme);
		return ResponseEntity.ok(filme);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		filmeService.deletar(id);
		return ResponseEntity.ok().build();
	}
}

package one.digitalinnovation.gof.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Filme;
import one.digitalinnovation.gof.model.Reserva;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.FilmeService;
import one.digitalinnovation.gof.service.ReservaService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author falvojr
 */
@RestController
@RequestMapping("reserva")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@GetMapping
	public ResponseEntity<Iterable<Reserva>> buscarTodos() {
		return ResponseEntity.ok(reservaService.buscarTodas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(reservaService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Reserva> alugar(@RequestBody Reserva reserva)   {
		Reserva reservaReturn = reservaService.alugarFilme(reserva.getFilme(), reserva.getCliente(), reserva.getDataDeEntrega());
		return ResponseEntity.ok(reservaReturn);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		reservaService.deletar(id);
		return ResponseEntity.ok().build();
	}
}

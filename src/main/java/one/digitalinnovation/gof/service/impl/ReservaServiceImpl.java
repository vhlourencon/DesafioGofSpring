package one.digitalinnovation.gof.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Filme;
import one.digitalinnovation.gof.model.Reserva;
import one.digitalinnovation.gof.model.ReservaRepository;
import one.digitalinnovation.gof.service.ReservaService;

/**
 * Implementação da <b>Strategy</b> {@link ReservaService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class ReservaServiceImpl implements ReservaService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ReservaRepository reservaRepository;

	// Strategy: Implementar os métodos definidos na interface.

	@Override
	public Iterable<Reserva> buscarTodas() {
		// Buscar todos os Clientes.
		return reservaRepository.findAll();
	}

	@Override
	public Reserva buscarPorId(Long id) {
		// Buscar Reserva por ID.
		Optional<Reserva> reservaOptional = reservaRepository.findById(id);
		return reservaOptional.get();
	}

	@Override
	public void inserir(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	@Override
	public void atualizar(Long id, Reserva reserva) {
		// Buscar Cliente por ID, caso exista:
		Optional<Reserva> reservaBD = reservaRepository.findById(id);
		if (reservaBD.isPresent()) {
			reservaRepository.save(reserva);
		}
	}

	@Override
	public void deletar(Long id) {

		reservaRepository.deleteById(id);
	}

	/**
	 * Implementar a Facade entre filmes e Cliente e Criar uma Reserva
	 * 
	 * @throws Exception
	 */
	@Override
	public Reserva alugarFilme(Filme filme, Cliente cliente, Date dataDeEntrega) {

		Iterable<Reserva> lista = this.buscarTodas();
		Date hoje = new Date();

		if (lista != null) {
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				Reserva reserva = (Reserva) iterator.next();
				if (reserva.getFilme().getId().compareTo(filme.getId()) == 0) {
					if (reserva.getDataDeEntrega().after(hoje)) {
						return null;
					}
				}

			}
		}
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setDataDeEntrega(dataDeEntrega);
		reserva.setDataDeLocacao(new Date());
		inserir(reserva);
		return reserva;
	}

}

package one.digitalinnovation.gof.service;

import java.util.Date;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Filme;
import one.digitalinnovation.gof.model.Reserva;

/**
 * Interface que define o padrão Strategy  do dominio de Reserva de Filme 
 * @author vhlourencon
 *
 */
public interface ReservaService {

	Iterable<Reserva> buscarTodas();

	Reserva buscarPorId(Long id);

	void inserir(Reserva reserva);

	void atualizar(Long id, Reserva reserva);

	void deletar(Long id);
	
	Reserva alugarFilme(Filme filme,Cliente cliente, Date dataDeDEvolucao)  ;

}

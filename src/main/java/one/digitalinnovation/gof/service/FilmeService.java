package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Filme;

/**
 * Interface que define o padrão Strategy  do dominio de  Filme
 * @author vhlourencon
 *
 */
public interface FilmeService {

	Iterable<Filme> buscarTodos();

	Filme buscarPorId(Long id);

	void inserir(Filme filme);

	void atualizar(Long id, Filme filme);

	void deletar(Long id);

}

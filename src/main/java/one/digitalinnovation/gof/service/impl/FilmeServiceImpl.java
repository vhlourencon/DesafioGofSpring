package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Filme;
import one.digitalinnovation.gof.model.FilmeRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.FilmeService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class FilmeServiceImpl implements FilmeService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private FilmeRepository filmeRepository;
	
	

	@Override
	public Iterable<Filme> buscarTodos() {

		return filmeRepository.findAll();
	}

	@Override
	public Filme buscarPorId(Long id) {
		Optional<Filme> filmeOptional = filmeRepository.findById(id);
		return filmeOptional.get();
	}

	@Override
	public void inserir(Filme filme) {
		filmeRepository.save(filme);
	}

	@Override
	public void atualizar(Long id, Filme filme) {
		// Buscar Cliente por ID, caso exista:
		Optional<Filme> filmeBD = filmeRepository.findById(id);
		if (filmeBD.isPresent()) {
			filmeRepository.save(filme);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Filme Por ID
		filmeRepository.deleteById(id);
	}

}

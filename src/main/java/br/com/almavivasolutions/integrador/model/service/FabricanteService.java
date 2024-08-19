package br.com.almavivasolutions.integrador.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import br.com.almavivasolutions.integrador.model.repository.FabricanteRepository;
import br.com.almavivasolutions.integrador.utils.logger.ApiLogger;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public Fabricante buscar(String nome) {
		Fabricante fabricante = fabricanteRepository.findByNome(nome);
		ApiLogger.logDatabaseDetails("Fabricante " + nome + " encontrado.");
		return fabricante;
	}
}

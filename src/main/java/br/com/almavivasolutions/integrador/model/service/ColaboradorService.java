package br.com.almavivasolutions.integrador.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.repository.ColaboradorRepository;
import br.com.almavivasolutions.integrador.utils.logger.ApiLogger;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public Colaborador buscar(String email) {
		Colaborador colaborador = colaboradorRepository.findByEmail(email);
		ApiLogger.logDatabaseDetails("Colaborador com e-mail " + email + " encontrador.");
		return colaborador;
	}

	public Map<String, Colaborador> buscarTodos() {
	    Map<String, Colaborador> colaboradorMap = colaboradorRepository.findAll()
	            .stream()
	            .collect(Collectors.toMap(
	                Colaborador::getEmail, 
	                colaborador -> colaborador,
	                (existing, replacement) -> replacement));
	    
	    ApiLogger.logDatabaseDetails(colaboradorMap.size() + " colaboradores encontrados.");
	    return colaboradorMap;
	}


}

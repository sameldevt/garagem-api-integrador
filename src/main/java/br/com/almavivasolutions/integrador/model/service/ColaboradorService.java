package br.com.almavivasolutions.integrador.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public Colaborador buscar(String email) {
		Colaborador colaborador = colaboradorRepository.findByEmail(email);
		return colaborador;
	}

	public Map<String, Colaborador> buscarTodos() {
		List<Colaborador> colaboradores = colaboradorRepository.findAll();
		
		Map<String, Colaborador> colaboradoresMap = new HashMap<String, Colaborador>();
		for(Colaborador colaborador : colaboradores) {
			colaboradoresMap.put(colaborador.getEmail(), colaborador);
		}
		
		return colaboradoresMap;
	}

}

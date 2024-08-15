package br.com.almavivasolutions.integrador.model.service;

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

}

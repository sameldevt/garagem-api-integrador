package br.com.almavivasolutions.integrador.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	Colaborador findByEmail(String email);

}

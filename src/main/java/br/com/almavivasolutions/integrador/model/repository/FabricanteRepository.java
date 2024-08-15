package br.com.almavivasolutions.integrador.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almavivasolutions.integrador.model.entity.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long>{
	Fabricante findByNome(String nome);
}

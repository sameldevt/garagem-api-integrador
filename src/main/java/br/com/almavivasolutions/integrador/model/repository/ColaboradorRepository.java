package br.com.almavivasolutions.integrador.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	@Query(value = "SELECT DISTINCT * FROM tb_colaborador tc WHERE tc.email = :email", 	nativeQuery = true)
	Colaborador findByEmail(@Param(value = "email") String email);

}

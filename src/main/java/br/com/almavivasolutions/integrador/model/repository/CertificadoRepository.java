package br.com.almavivasolutions.integrador.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoAvaliacao;

@Repository
public interface CertificadoRepository extends JpaRepository<CertificadoAvaliacao, Long>{
}

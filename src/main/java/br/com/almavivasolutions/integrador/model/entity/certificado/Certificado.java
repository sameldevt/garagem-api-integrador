package br.com.almavivasolutions.integrador.model.entity.certificado;


public interface Certificado {
	Certificado build(String info);
	CertificadoAvaliacao toCertificadoAvaliacao();
}

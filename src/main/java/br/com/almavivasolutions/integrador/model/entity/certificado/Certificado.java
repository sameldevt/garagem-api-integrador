package br.com.almavivasolutions.integrador.model.entity.certificado;


public interface Certificado {
	Certificado deserialize(String info);
	CertificadoAvaliacao toCertificadoAvaliacao();
}

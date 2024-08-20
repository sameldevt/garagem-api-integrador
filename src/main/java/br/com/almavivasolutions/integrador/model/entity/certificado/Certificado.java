package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.util.List;

public interface Certificado {
	Certificado deserialize(List<String> info);
	CertificadoAvaliacao toCertificadoAvaliacao();
	String getHeader();
	String getCollaboratorName();
}

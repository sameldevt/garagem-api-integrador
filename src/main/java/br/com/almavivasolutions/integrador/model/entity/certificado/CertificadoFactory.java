package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.util.HashMap;
import java.util.Map;

public enum CertificadoFactory {
	GOOGLE_CLOUD(new CertificadoGoogleCloud()),
	IBM(new CertificadoIBM()),
	RED_HAT(new CertificadoRedHat()),
	LIFERAY(new CertificadoLiferay()),
	DELPHIX(new CertificadoDelphix()),
	AWS(new CertificadoAWS());

	private final Certificado certificado;

	CertificadoFactory(Certificado certificado) {
		this.certificado = certificado;
	}

	public Certificado getCertificado() {
		return certificado;
	}

	public static Certificado get(String tipo) {
		try {
			return CertificadoFactory.valueOf(tipo.toUpperCase().replace(" ", "_")).getCertificado();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Opção '" + tipo + "' indisponível", e);
		}
	}
}

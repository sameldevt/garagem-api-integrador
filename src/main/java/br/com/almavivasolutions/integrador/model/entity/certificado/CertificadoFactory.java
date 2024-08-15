package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.util.HashMap;
import java.util.Map;

public class CertificadoFactory {
	private static final Map<String, Certificado> strategies = new HashMap<String, Certificado>();
	
	static {
		strategies.put("Google Cloud", new CertificadoGoogleCloud());
		strategies.put("IBM", new CertificadoIBM());
		strategies.put("Red Hat", new CertificadoRedHat());
		strategies.put("Liferay", new CertificadoLiferay());
		strategies.put("Delphix", new CertificadoDelphix());
		strategies.put("AWS", new CertificadoAWS());
	}
	
	public static Certificado getCertificado(String tipo) {
		Certificado certificado = strategies.get(tipo);
		
		if(certificado == null) {
			throw new IllegalArgumentException("");
		}
		
		return certificado;
	}
}

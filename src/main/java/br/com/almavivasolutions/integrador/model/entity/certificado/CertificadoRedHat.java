package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.almavivasolutions.integrador.util.parser.CsvParser;
import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

public class CertificadoRedHat implements Certificado{
	private String certificationIndex;
  	private String certificationType;
  	private String accreditationName;
  	private String level;
  	private String program;
  	private String specialization;
  	private String function;
  	private String department;
  	private String superior;
  	private String accreditationHolderFullName;
  	private String accreditationHolderEmail;
  	private String isUserActive;
  	private String credentialEarnedDate;
  	private String credentialExpirationDate;
  	private String renewalLimitDate;
  	private String note;
  	
  	@Override
  	public CertificadoRedHat deserialize(String line) {
  		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  		if(line.contains("\"")) {
  			line = line.replace("\"", "");
  		}
  		
  		String[] lineSplit = line.split(",");
  		if(lineSplit.length != 16) {
  	    	line = line + " ,";
  		}
  		List<String> columns = CsvParser.lineToColumns(line);
  		
  		System.out.println(columns.size() + " : " + columns);
  		certificationIndex = columns.get(0).trim();
  		certificationType = columns.get(1).trim();
  		accreditationName = columns.get(2).trim();
  		level = columns.get(3).trim();
  		program = columns.get(4).trim();
  		specialization = columns.get(5).trim();
  		function = columns.get(6).trim();
  		department = columns.get(7).trim();
  		superior = columns.get(8).trim();
  		accreditationHolderFullName = columns.get(9).trim();
  		accreditationHolderEmail = columns.get(10).trim();
  		isUserActive = columns.get(11).trim();
  		credentialEarnedDate = columns.get(12).trim();
  		credentialExpirationDate = columns.get(13).trim();
  		renewalLimitDate = columns.get(14).trim();
  		note = columns.get(15).trim();
  		
  		return this;
  	}
  	
  	@Override
	public CertificadoAvaliacao toCertificadoAvaliacao() {
		CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
		certificadoAvaliacao.setTipoCertificado(certificationType);
		certificadoAvaliacao.setNome(accreditationName);
		certificadoAvaliacao.setEmail(accreditationHolderEmail);
		certificadoAvaliacao.setTipoCertificado(specialization);
		certificadoAvaliacao.setDataRealizacao(LocalDateParser.toLocalPattern(credentialEarnedDate));
		certificadoAvaliacao.setDataLimiteRenovacao(LocalDateParser.toLocalPattern(renewalLimitDate));
		certificadoAvaliacao.setDataExpiracao(LocalDateParser.toLocalPattern(credentialExpirationDate));
		certificadoAvaliacao.setObservacao(note);
		certificadoAvaliacao.setValido(false);
		certificadoAvaliacao.setPendente(true);
		return certificadoAvaliacao;
	}

	@Override
	public String toString() {
		return "CertificadoRedHat [certificationIndex=" + certificationIndex + ", certificationType="
				+ certificationType + ", accreditationName=" + accreditationName + ", level=" + level + ", program="
				+ program + ", specialization=" + specialization + ", function=" + function + ", department="
				+ department + ", superior=" + superior + ", accreditationHolderFullName=" + accreditationHolderFullName
				+ ", accreditationHolderEmail=" + accreditationHolderEmail + ", isUserActive=" + isUserActive
				+ ", credentialEarnedDate=" + credentialEarnedDate + ", credentialExpirationDate="
				+ credentialExpirationDate + ", renewalLimitDate=" + renewalLimitDate + ", note=" + note + "]";
	}
  	
  	
}

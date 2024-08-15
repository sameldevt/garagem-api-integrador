package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.almavivasolutions.integrador.util.parser.CsvParser;
import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

public class CertificadoDelphix implements Certificado{
	private String certificationIndex;
	private String credentialName;
	private String department;
	private String superior;
	private String collaboratorName;
	private String collaboratorEmailAddress;
	private String isActive;
	private String credentialEarnedDate;
	private String credentialExpirationDate;
	private String renewalLimitDate;
	private String note;
	
	@Override
	public CertificadoDelphix build(String line) {
		if(line.contains("\"")) {
  			line = line.replace("\"", "");
  		}
  		
  		String[] lineSplit = line.split(",");
  		if(lineSplit.length != 11) {
  	    	line = line + " ,";
  		}
  		List<String> columns = CsvParser.lineToColumns(line);
  		
  		certificationIndex = columns.get(0).trim();
  		credentialName = columns.get(1).trim();
  		department = columns.get(2).trim();
  		superior = columns.get(3).trim();
  		collaboratorName = columns.get(4).trim();
  		collaboratorEmailAddress = columns.get(5).trim();
  		isActive = columns.get(6).trim();
  		credentialEarnedDate = columns.get(7).trim();
  		credentialExpirationDate = columns.get(8).trim();
  		renewalLimitDate = columns.get(9).trim();
  		note = columns.get(10).trim();	
  		
  		return this;
	}
	
	@Override
	public CertificadoAvaliacao toCertificadoAvaliacao() {
		CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
		certificadoAvaliacao.setNome(credentialName);
		certificadoAvaliacao.setEmail(collaboratorEmailAddress);
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
		return "CertificadoDelphix [certificationIndex=" + certificationIndex + "credentialName=" + credentialName + ", departament=" + department + ", superior="
				+ superior + ", collaboratorName=" + collaboratorName + ", collaboratorEmailAddress="
				+ collaboratorEmailAddress + ", isActive=" + isActive + ", credentialEarnedDate=" + credentialEarnedDate
				+ ", credentialExpirationDate=" + credentialExpirationDate + ", renewalLimitDate=" + renewalLimitDate
				+ ", note=" + note + "]";
	}
	
}

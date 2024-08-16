package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;
import java.util.List;

import br.com.almavivasolutions.integrador.util.parser.CsvParser;
import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

public class CertificadoLiferay implements Certificado{
	private String certificationIndex;
	private String certification;
	private String technology;
	private String departament;
	private String superior;
	private String collaboratorName;
	private String collaboratorEmailAddress;
	private String isActiveInTheCompany;
	private String credentialEarnedDate;
	private String credentialExpirationDate;
	private String renewalLimitDate;
	private String note;
	
	@Override
	public CertificadoLiferay deserialize(String line) {
		if(line.contains("\"")) {
  			line = line.replace("\"", "");
  		}
  		
  		String[] lineSplit = line.split(",");
  		if(lineSplit.length != 12) {
  	    	line = line + " ,";
  		}
  		List<String> columns = CsvParser.lineToColumns(line);
  		
  		certificationIndex = columns.get(0).trim();
  		certification = columns.get(1).trim();
  		technology = columns.get(2).trim();
  		departament = columns.get(3).trim();
  		superior = columns.get(4).trim();
  		collaboratorName = columns.get(5).trim();
  		collaboratorEmailAddress = columns.get(6).trim();
  		isActiveInTheCompany = columns.get(7).trim();
  		credentialEarnedDate = columns.get(8).trim();
  		credentialExpirationDate = columns.get(9).trim();
  		renewalLimitDate = columns.get(10).trim();
  		note = columns.get(11).trim();
  		
  		return this;
	}
	
	@Override
	public CertificadoAvaliacao toCertificadoAvaliacao() {
		CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
		certificadoAvaliacao.setNome(certification);
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
		return "CertificadoLiferay [certificationIndex="+certificationIndex+"certification=" + certification + ", technology=" + technology + ", departament="
				+ departament + ", superior=" + superior + ", collaboratorName=" + collaboratorName
				+ ", collaboratorEmailAddress=" + collaboratorEmailAddress + ", isActiveInTheCompany="
				+ isActiveInTheCompany + ", credentialEarnedDate=" + credentialEarnedDate
				+ ", credentialExpirationDate=" + credentialExpirationDate + ", renewalLimitDate=" + renewalLimitDate
				+ ", note=" + note + "]";
	}

}

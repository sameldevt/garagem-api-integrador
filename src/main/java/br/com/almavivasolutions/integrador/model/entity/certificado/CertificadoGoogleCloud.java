package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.almavivasolutions.integrador.util.parser.CsvParser;
import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

public class CertificadoGoogleCloud implements Certificado{
	private String certificationIndex;
	private String certificationNumber;
	private String certificationName;
	private String certificationType;
	private String certificationSubType;
	private String department;
	private String superior;
	private String collaboratorFirstName;
	private String collaboratorLastName;
	private String collaboratorEmailAddress;
	private String isActiveinTheCompany;
	private String expirationDate;
	private String renewalLimitDate;
	private String note;

	@Override
	public CertificadoGoogleCloud deserialize(String line) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  		if(line.contains("\"")) {
  			line = line.replace("\"", "");
  		}
  		
  		String[] lineSplit = line.split(",");
  		if(lineSplit.length != 14) {
  	    	line = line + " ,";
  		}
  		List<String> columns = CsvParser.lineToColumns(line);
  		
  		certificationIndex = columns.get(0).trim();
  		certificationNumber = columns.get(1).trim();
  		certificationName = columns.get(2).trim();
  		certificationType = columns.get(3).trim();
  		certificationSubType = columns.get(4).trim();
  		department = columns.get(5).trim();
  		superior = columns.get(6).trim();
  		collaboratorFirstName = columns.get(7).trim();
  		collaboratorLastName = columns.get(8).trim();
  		collaboratorEmailAddress = columns.get(9).trim();
  		isActiveinTheCompany = columns.get(10).trim();
  		expirationDate = columns.get(11).trim();
  		renewalLimitDate = columns.get(12).trim();
  		note = columns.get(13).trim();
  		
  		return this;
	}
	
	@Override
	public CertificadoAvaliacao toCertificadoAvaliacao() {
		CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
		certificadoAvaliacao.setNome(certificationName);
		certificadoAvaliacao.setIdCertificadoFabricante(certificationNumber);
		certificadoAvaliacao.setTipoCertificado(certificationType);
		certificadoAvaliacao.setSubTipoCertificado(certificationSubType);
		certificadoAvaliacao.setEmail(collaboratorEmailAddress);
		certificadoAvaliacao.setDataLimiteRenovacao(LocalDateParser.toLocalPattern(renewalLimitDate));
		certificadoAvaliacao.setDataExpiracao(LocalDateParser.toLocalPattern(expirationDate));
		certificadoAvaliacao.setObservacao(note);
		certificadoAvaliacao.setValido(false);
		certificadoAvaliacao.setPendente(true);
		return certificadoAvaliacao;
	}

	@Override
	public String toString() {
		return "CertificadoGoogleCloud [certificationIndex=" + certificationIndex + "certificationNumber=" + certificationNumber + ", certificationName="
				+ certificationName + ", certificationType=" + certificationType + ", certificationSubType="
				+ certificationSubType + ", departament=" + department + ", superior=" + superior
				+ ", collaboratorFirstName=" + collaboratorFirstName + ", collaboratorLastName=" + collaboratorLastName
				+ ", collaboratorEmailAddress=" + collaboratorEmailAddress + ", isActiveinTheCompany="
				+ isActiveinTheCompany + ", expirationDate=" + expirationDate + ", renewalLimitDate=" + renewalLimitDate
				+ ", note=" + note + "]";
	}
	
}

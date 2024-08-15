package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.almavivasolutions.integrador.util.parser.CsvParser;
import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

public class CertificadoIBM implements Certificado{
	private String certificationIndex;
	private String credentialType;
	private String solution;
	private String badgeName;
	private String salesOrTechnical;
	private String collaboratorFirstName;
	private String collaboratorLastName;
	private String collaboratorEmailAddress;
	private String isActiveInTheCompany;
	private String department;
	private String superior;
	private String ibmId;
	private String endUserUniqueID;
	private String credentialEarnedDate;
	private String credentialExpirationDate;
	private String renewalLimitDate;
	private String note;

	@Override
	public CertificadoIBM build(String line) {
  		if(line.contains("\"")) {
  			line = line.replace("\"", "");
  		}
  		
  		String[] lineSplit = line.split(",");
  		if(lineSplit.length != 17) {
  	    	line = line + " ,";
  		}
  		List<String> columns = CsvParser.lineToColumns(line);
  		
  		certificationIndex = columns.get(0).trim();
  		credentialType = columns.get(1).trim();
  		solution = columns.get(2).trim();
  		badgeName = columns.get(3).trim();
  		salesOrTechnical = columns.get(4).trim();
  		collaboratorFirstName = columns.get(5).trim();
  		collaboratorLastName = columns.get(6).trim();
  		collaboratorEmailAddress = columns.get(7).trim();
  		isActiveInTheCompany = columns.get(8).trim();
  		department = columns.get(9).trim();
  		superior = columns.get(10).trim();
  		ibmId = columns.get(11).trim();
  		endUserUniqueID = columns.get(12).trim();
  		credentialEarnedDate = columns.get(13).trim();
  		credentialExpirationDate = columns.get(14).trim();
  		renewalLimitDate = columns.get(15).trim();
  		note = columns.get(16).trim();
  		
  		return this;
	}
	
	@Override
	public CertificadoAvaliacao toCertificadoAvaliacao() {
		CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
		certificadoAvaliacao.setTipoCertificado(credentialType);
		certificadoAvaliacao.setSubTipoCertificado(solution);
		certificadoAvaliacao.setNome(badgeName);
		certificadoAvaliacao.setTipoCertificado(salesOrTechnical);
		certificadoAvaliacao.setEmail(collaboratorEmailAddress);
		certificadoAvaliacao.setIdCertificadoFabricante(ibmId);
		try {
			certificadoAvaliacao.setDataRealizacao(LocalDateParser.toLocalPattern(credentialEarnedDate));
			certificadoAvaliacao.setDataLimiteRenovacao(LocalDateParser.toLocalPattern(renewalLimitDate));
			certificadoAvaliacao.setDataExpiracao(LocalDateParser.toLocalPattern(credentialExpirationDate));
		}catch(DateTimeParseException e) {
			System.out.println(this);
		}
		
		certificadoAvaliacao.setObservacao(note);
		certificadoAvaliacao.setValido(false);
		certificadoAvaliacao.setPendente(true);
		return certificadoAvaliacao;
	}

	@Override
	public String toString() {
		return "CertificadoIBM [credentialType=" + credentialType + ", solution=" + solution + ", badgeName="
				+ badgeName + ", salesOrTechnical=" + salesOrTechnical + ", collaboratorFirstName="
				+ collaboratorFirstName + ", collaboratorLastName=" + collaboratorLastName
				+ ", collaboratorEmailAddress=" + collaboratorEmailAddress + ", isActiveInTheCompany="
				+ isActiveInTheCompany + ", department=" + department + ", superior=" + superior + ", ibmId=" + ibmId
				+ ", endUserUniqueID=" + endUserUniqueID + ", credentialEarnedDate=" + credentialEarnedDate
				+ ", credentialExpirationDate=" + credentialExpirationDate + ", renewalLimitDate=" + renewalLimitDate
				+ ", note=" + note + "]";
	}
	
	
}

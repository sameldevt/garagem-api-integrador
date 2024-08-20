package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.almavivasolutions.integrador.utils.parser.LocalDateParser;

public class CertificadoIBM implements Certificado {
    private final String header = ",Credential Type,Solution,Badge Name,Sales/Technical,First Name,Last Name,Email Address,Active in the company?,Departament,Superior,IBM ID,End User Unique ID,Credential Earned Date,Credential Expiration Date,Data Limite para Renovação,Observação";
    private String credentialType;
    private String solution;
    private String badgeName;
    private String salesOrTechnical;
    private String collaboratorName;
    private String collaboratorEmailAddress;
    private String credentialEarnedDate;
    private String credentialExpirationDate;
    private String renewalLimitDate;
    private String note;

    @Override
    public CertificadoIBM deserialize(List<String> columns) {
        credentialType = columns.get(1).trim();
        solution = columns.get(2).trim();
        badgeName = columns.get(3).trim();
        salesOrTechnical = columns.get(4).trim();
        String collaboratorFirstName = columns.get(5).trim();
        String collaboratorLastName = columns.get(6).trim();
        collaboratorName = collaboratorFirstName + " " + collaboratorLastName;
        collaboratorEmailAddress = columns.get(7).trim();
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
        try {
            certificadoAvaliacao.setDataRealizacao(LocalDateParser.toLocalPattern(credentialEarnedDate));
            certificadoAvaliacao.setDataLimiteRenovacao(LocalDateParser.toLocalPattern(renewalLimitDate));
            certificadoAvaliacao.setDataExpiracao(LocalDateParser.toLocalPattern(credentialExpirationDate));
        } catch (DateTimeParseException e) {
            return null;
        }

        certificadoAvaliacao.setObservacao(note);
        certificadoAvaliacao.setValido(false);
        certificadoAvaliacao.setPendente(true);
        return certificadoAvaliacao;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "CertificadoIBM{" +
                "credentialType='" + credentialType + '\'' +
                ", solution='" + solution + '\'' +
                ", badgeName='" + badgeName + '\'' +
                ", salesOrTechnical='" + salesOrTechnical + '\'' +
                ", collaboratorEmailAddress='" + collaboratorEmailAddress + '\'' +
                ", credentialEarnedDate='" + credentialEarnedDate + '\'' +
                ", credentialExpirationDate='" + credentialExpirationDate + '\'' +
                ", renewalLimitDate='" + renewalLimitDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

	@Override
	public String getCollaboratorName() {
		return collaboratorName;
	}
}

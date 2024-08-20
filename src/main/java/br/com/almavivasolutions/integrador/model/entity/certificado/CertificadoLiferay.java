package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.almavivasolutions.integrador.utils.parser.LocalDateParser;

public class CertificadoLiferay implements Certificado {
    private final String header = ",Certificação,Tecnologia,Departament,Superior,Nome,Email Address,Active,Data Realizada,Data de expiração,Data Limite para Renovação,Observação";
    private String certification;
    private String technology;
    private String collaboratorName;
    private String collaboratorEmailAddress;
    private String credentialEarnedDate;
    private String credentialExpirationDate;
    private String renewalLimitDate;
    private String note;

    @Override
    public CertificadoLiferay deserialize(List<String> columns) {
        certification = columns.get(1).trim();
        technology = columns.get(2).trim();
        String collaboratorFirstName = columns.get(4).trim();
        String collaboratorLastName = columns.get(5).trim();
        collaboratorName = collaboratorFirstName + " " + collaboratorLastName;
        collaboratorEmailAddress = columns.get(6).trim();
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
        certificadoAvaliacao.setTipoCertificado(technology);
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
        return "CertificadoLiferay{" +
                "certification='" + certification + '\'' +
                ", technology='" + technology + '\'' +
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

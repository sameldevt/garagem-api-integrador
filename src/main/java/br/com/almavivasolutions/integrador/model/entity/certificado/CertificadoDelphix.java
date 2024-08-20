package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.almavivasolutions.integrador.utils.parser.LocalDateParser;

public class CertificadoDelphix implements Certificado {
    private final String header = ",Certificação / Badge,Departament,Superior,Nome,Email Address,Active,Credential Earned Date,Credential Expiration Date,Data Limite para Renovação,Observação";
    private String credentialName;
    private String collaboratorName;
    private String collaboratorEmailAddress;
    private String credentialEarnedDate;
    private String credentialExpirationDate;
    private String renewalLimitDate;
    private String note;

    @Override
    public CertificadoDelphix deserialize(List<String> columns) {
        credentialName = columns.get(1).trim();
        collaboratorName = columns.get(4).trim();
        collaboratorEmailAddress = columns.get(5).trim();
        credentialEarnedDate = columns.get(7).trim();
        credentialExpirationDate = columns.get(8).trim();
        renewalLimitDate = columns.get(9).trim();
        note = columns.get(10).trim();

        return this;
    }

    @Override
    public CertificadoAvaliacao toCertificadoAvaliacao() {
        CertificadoAvaliacao certificadoAvaliacao = new CertificadoAvaliacao();
        credentialName = credentialName.replace("\n", " ");
        certificadoAvaliacao.setNome(credentialName);
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
        return "CertificadoDelphix{" +
                "credentialName='" + credentialName + '\'' +
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

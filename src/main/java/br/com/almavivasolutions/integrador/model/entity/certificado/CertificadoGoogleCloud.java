package br.com.almavivasolutions.integrador.model.entity.certificado;

import br.com.almavivasolutions.integrador.util.parser.LocalDateParser;

import java.time.format.DateTimeParseException;
import java.util.List;

public class CertificadoGoogleCloud implements Certificado {
    private final String header = ",Certification Number,Certification Name,Certification Type,Certification Sub-Type,Departament,Superior,First Name,Last Name,E-mail,Active in the company?,Certification Expiration Date,Data Limite para Renovação,Observação";
    private String certificationNumber;
    private String certificationName;
    private String certificationType;
    private String certificationSubType;
    private String collaboratorEmailAddress;
    private String expirationDate;
    private String renewalLimitDate;
    private String note;

    @Override
    public CertificadoGoogleCloud deserialize(List<String> columns) {
        certificationNumber = columns.get(1).trim();
        certificationName = columns.get(2).trim();
        certificationType = columns.get(3).trim();
        certificationSubType = columns.get(4).trim();
        collaboratorEmailAddress = columns.get(9).trim();
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
        try {
            certificadoAvaliacao.setDataLimiteRenovacao(LocalDateParser.toLocalPattern(renewalLimitDate));
            certificadoAvaliacao.setDataExpiracao(LocalDateParser.toLocalPattern(expirationDate));
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
        return "CertificadoGoogleCloud{" +
                "certificationNumber='" + certificationNumber + '\'' +
                ", certificationName='" + certificationName + '\'' +
                ", certificationType='" + certificationType + '\'' +
                ", certificationSubType='" + certificationSubType + '\'' +
                ", collaboratorEmailAddress='" + collaboratorEmailAddress + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", renewalLimitDate='" + renewalLimitDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

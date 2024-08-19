package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.format.DateTimeParseException;
import java.util.List;

import br.com.almavivasolutions.integrador.utils.parser.LocalDateParser;

public class CertificadoRedHat implements Certificado {
    private final String header = ",  ,Accreditation Name_New,Level,Programa,Specialization,Function,Departament,Superior,Accreditation Holder: Full Name,Accreditation Holder: Email,Usuário Ativo,Credential Earned Date,Credential Expiration Date,Data Limite para Renovação,Observação";
    private String certificationType;
    private String accreditationName;
    private String level;
    private String program;
    private String specialization;
    private String function;
    private String accreditationHolderEmail;
    private String credentialEarnedDate;
    private String credentialExpirationDate;
    private String renewalLimitDate;
    private String note;

    @Override
    public CertificadoRedHat deserialize(List<String> columns) {
        certificationType = columns.get(1).trim();
        accreditationName = columns.get(2).trim();
        level = columns.get(3).trim();
        program = columns.get(4).trim();
        specialization = columns.get(5).trim();
        function = columns.get(6).trim();
        accreditationHolderEmail = columns.get(10).trim();
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
        return "CertificadoRedHat{" +
                "certificationType='" + certificationType + '\'' +
                ", accreditationName='" + accreditationName + '\'' +
                ", level='" + level + '\'' +
                ", program='" + program + '\'' +
                ", specialization='" + specialization + '\'' +
                ", function='" + function + '\'' +
                ", accreditationHolderEmail='" + accreditationHolderEmail + '\'' +
                ", credentialEarnedDate='" + credentialEarnedDate + '\'' +
                ", credentialExpirationDate='" + credentialExpirationDate + '\'' +
                ", renewalLimitDate='" + renewalLimitDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

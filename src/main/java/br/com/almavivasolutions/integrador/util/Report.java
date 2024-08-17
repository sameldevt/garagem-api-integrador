package br.com.almavivasolutions.integrador.util;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Report {
    private final Instant requestStartTime;
    private Instant requestEndTime;
    private int totalCertificates;
    private int certificatesInserted;
    private int certificatesNotInserted;
    private int collaboratorsNotFound;

    private final List<String> certificatesNotInsertedList;
    private final Set<String> collaboratorsNotFoundList;

    public Report() {
        this.requestStartTime = Instant.now();
        this.certificatesInserted = 0;
        this.certificatesNotInserted = 0;
        this.collaboratorsNotFound = 0;
        this.certificatesNotInsertedList = new ArrayList<>();
        this.collaboratorsNotFoundList = new HashSet<>();
    }

    public void endRequest() {
        this.requestEndTime = Instant.now();
    }

    public void setTotalCertificates(int totalCertificates) {
        this.totalCertificates = totalCertificates;
    }

    public void incrementCertificatesInserted() {
        this.certificatesInserted++;
    }

    public void addCertificateNotInserted(String certificate) {
        this.certificatesNotInserted++;
        this.certificatesNotInsertedList.add(certificate);
    }

    public void addCollaboratorNotFound(String collaborator) {
        this.collaboratorsNotFound++;
        this.collaboratorsNotFoundList.add(collaborator);
    }

    private Duration getRequestDuration() {
        if (requestEndTime == null) {
            throw new IllegalStateException("A requisição ainda não foi finalizada.");
        }
        return Duration.between(requestStartTime, requestEndTime);
    }

    private double getCertificatesInsertedPercentage() {
        if (totalCertificates == 0) {
            return 0.0;
        }
        return (double) certificatesInserted / totalCertificates * 100;
    }

    private double getCertificatesNotInsertedPercentage() {
        if (totalCertificates == 0) {
            return 0.0;
        }
        return (double) certificatesNotInserted / totalCertificates * 100;
    }

    private double getCollaboratorsNotFoundPercentage() {
        if (totalCertificates == 0) {
            return 0.0;
        }
        return (double) collaboratorsNotFound / totalCertificates * 100;
    }

    private String getCertificatesNotInsertedListToString() {
        int certificatesWithoutName = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n{\n");
        for (String certificateName : certificatesNotInsertedList) {
            if (certificateName.isBlank()) {
                certificatesWithoutName++;
                continue;
            }
            stringBuilder.append("      ").append(certificateName).append("\n");
        }

        if (certificatesWithoutName != 0) {
            stringBuilder.append("      + ").append(certificatesWithoutName).append(" certificado(s) sem nome.\n");
        }
        return stringBuilder.append("}").toString();
    }

    private String getCollaboratorsNotFoundListToString() {
        int collaboratorsWithoutEmail = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n{\n");
        for (String collaboratorName : collaboratorsNotFoundList) {
            if (collaboratorName.isBlank()) {
                collaboratorsWithoutEmail++;
                continue;
            }
            stringBuilder.append("      ").append(collaboratorName).append("\n");
        }

        if (collaboratorsWithoutEmail != 0) {
            stringBuilder.append("      + ").append(collaboratorsWithoutEmail).append(" colaborador(es) sem e-mail.\n");
        }
        return stringBuilder.append("}").toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Tempo da Requisição: ").append(getRequestDuration().toMillis() + " ms");
        stringBuilder.append("\nTotal de Certificados: ").append(totalCertificates);
        stringBuilder.append("\nCertificados Inseridos: ").append(certificatesInserted).append(" (").append(String.format("%.2f", getCertificatesInsertedPercentage())).append("%)");

        if (certificatesNotInserted != 0) {
            stringBuilder.append("\nCertificados Não Inseridos: ").append(certificatesNotInserted).append(" (").append(String.format("%.2f", getCertificatesNotInsertedPercentage())).append("%)");
        }
        if (collaboratorsNotFound != 0) {
            stringBuilder.append("\nColaboradores Não Encontrados: ").append(collaboratorsNotFound).append(" (").append(String.format("%.2f", getCollaboratorsNotFoundPercentage())).append("%)");
        }
        if (!certificatesNotInsertedList.isEmpty()) {
            stringBuilder.append("\nLista de Certificados Não Inseridos: ").append(certificatesNotInsertedList.isEmpty() ? "" : getCertificatesNotInsertedListToString());
        }

        if (!collaboratorsNotFoundList.isEmpty()) {
            stringBuilder.append("\nLista de Colaboradores Não Encontrados: ").append(collaboratorsNotFoundList.isEmpty() ? "" : getCollaboratorsNotFoundListToString());
        }

        return stringBuilder.toString();
    }
}
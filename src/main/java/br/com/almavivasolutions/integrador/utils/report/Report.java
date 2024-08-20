package br.com.almavivasolutions.integrador.utils.report;

import java.util.ArrayList;
import java.util.List;

import br.com.almavivasolutions.integrador.utils.email.EmailSender;
import br.com.almavivasolutions.integrador.utils.logger.ApiLogger;
import br.com.almavivasolutions.integrador.utils.pdf.PdfObject;

public class Report {
	private String nomeFabricante;
    private int totalCertificates;
    private int certificatesInserted;
    private int certificatesNotInserted;
    private int collaboratorsNotFound;

    private final List<CertificateNotInsertedInfo> certificateNotInserted;

    public Report() {
        this.certificatesInserted = 0;
        this.certificatesNotInserted = 0;
        this.collaboratorsNotFound = 0;
        this.certificateNotInserted = new ArrayList<>();
        ApiLogger.logRequestDetails("Novo relatório iniciado.");
    }
    
    public Integer getCertificatesInserted() {
		return certificatesInserted;
	}
    
    private void sendEmailReport() {
    	StringBuilder stringBuilder = new StringBuilder();

    	stringBuilder.append("Fabricante: ").append(nomeFabricante);
        stringBuilder.append("\nTotal de Certificados: ").append(totalCertificates);
        stringBuilder.append("\nCertificados não inseridos: ").append(certificatesNotInserted).append(" (").append(String.format("%.2f", getCertificatesNotInsertedPercentage())).append("%)");
        stringBuilder.append("\nMotivo: colaboradores não encontrados.");
        
        String pdfTableContent = stringBuilder.toString();
        String[] pdfTableHeader = new String[] {"Certificado", "Nome colaborador", "Email colaborador"};
        String pdfTableTitle = "Certificados não registrados:";
        String pdfTitle = "Relatório integrador certificações";
        
        PdfObject pdfObject = new PdfObject(
        		pdfTableContent, 
        		pdfTitle, 
        		certificateNotInserted, 
        		pdfTableHeader, 
        		pdfTableTitle);
        
        		EmailSender.sendEmail(
        		//"mmmorilhas@gmail.com", 
        		"steixeira@almavivasolutions.com.br",
        		"Relatório integrador certificações", 
        		pdfObject);
    }

    public void endRequest() {
        ApiLogger.logRequestDetails("Relatório finalizado.");
        if(collaboratorsNotFound > 0 || certificatesNotInserted > 0) {
        	sendEmailReport();        	
        }
    }
    
    public void setNomeFabricante(String nomeFabricante) {
    	this.nomeFabricante = nomeFabricante;
    }

    public void setTotalCertificates(int totalCertificates) {
        this.totalCertificates = totalCertificates;
        ApiLogger.logRequestDetails("Total de certificados alterado para " + totalCertificates);
    }

    public void incrementCertificatesInserted() {
        this.certificatesInserted++;
        ApiLogger.logRequestDetails("Total de certificados inseridos alterado para " + certificatesInserted);
    }

    public void addToCertificateNotInsertedList(String certificate, String collaboratorName, String collaboratorEmail) {
    	this.certificatesNotInserted++;
    	this.collaboratorsNotFound++;
    	CertificateNotInsertedInfo certificateNotFoundInfo = new CertificateNotInsertedInfo();
    	certificateNotFoundInfo.setCertitificateName(certificate);
    	certificateNotFoundInfo.setCollaboratorName(collaboratorName);
    	certificateNotFoundInfo.setCollaboratorEmail(collaboratorEmail);
    	certificateNotInserted.add(certificateNotFoundInfo);

        ApiLogger.logRequestDetails("Total de certificados não inseridos alterado para " + certificatesNotInserted);
    	ApiLogger.logRequestDetails("Total de colaboradores não encontrados alterado para " + collaboratorsNotFound);
    }

    private double getCertificatesNotInsertedPercentage() {
        if (totalCertificates == 0) {
            return 0.0;
        }
        return (double) certificatesNotInserted / totalCertificates * 100;
    }

}

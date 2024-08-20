package br.com.almavivasolutions.integrador.utils.parser;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.almavivasolutions.integrador.utils.pdf.PdfObject;
import br.com.almavivasolutions.integrador.utils.report.CertificateNotInsertedInfo;

public class PdfParser {
	private Font timesRomanBold18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font timesRomanBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font timesRomanNormal12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    
    private LocalDateTime dateTime = LocalDateTime.now();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private final String content;
    private final String documentTitle;
    private final String tableTitle;
    private final String[] tableHeaders;
    private final List<CertificateNotInsertedInfo> tableContent;
    
    public PdfParser(PdfObject pdfObject) {
    	this.content = pdfObject.getContent();
		this.tableContent = pdfObject.getTableContent(); 
		this.tableTitle = pdfObject.getTableTitle();
		this.tableHeaders = pdfObject.getTableHeaders(); 
		this.documentTitle = pdfObject.getDocumentTitle(); 
    }
    
	public ByteArrayOutputStream parse() {
		String reportMessage = "Relatório gerado às " + dateTime.format(formatter) + " de " + dateTime.format(formatter2);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            addTitlePage(document, documentTitle, timesRomanBold18);
            addTitlePage(document, reportMessage, timesRomanNormal12);
            addContent(document, content, timesRomanNormal12);
            createTable(document);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar documento pdf. " + e.getMessage());
        }
		
		return outputStream;
	}

    private void addTitlePage(Document document, String title, Font font)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(title, font));
        document.add(preface);
    }

    private void addContent(Document document, String content, Font font) throws DocumentException {
    	Paragraph p = new Paragraph();
        addEmptyLine(p, 1);
        document.add(p);
        document.add(new Paragraph(content, font));
    }
    
    private void createTable(Document document) throws DocumentException {
	    PdfPTable table = new PdfPTable(tableHeaders.length);
	    table.setWidthPercentage(100); 
	
	    for (String header : tableHeaders) {
	        PdfPCell c1 = new PdfPCell(new Phrase(header));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	    }
	    
	    Paragraph p = new Paragraph();
        addEmptyLine(p, 1);
        document.add(p);
	    addContent(document, tableTitle, timesRomanBold12);
        document.add(p);
        
	    table.setHeaderRows(1);
	
	    for (int i = 0; i < tableContent.size(); i++) {
	    	CertificateNotInsertedInfo certificateNotInsertedInfo = tableContent.get(i);
	        table.addCell(new PdfPCell(new Phrase(certificateNotInsertedInfo.getCertitificateName(), timesRomanNormal12)));    
	        table.addCell(new PdfPCell(new Phrase(certificateNotInsertedInfo.getCollaboratorEmail(), timesRomanNormal12)));  
	        table.addCell(new PdfPCell(new Phrase(certificateNotInsertedInfo.getCollaboratorName(), timesRomanNormal12)));  
	    }
	
	    document.add(table);
	}
    
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

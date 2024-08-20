package br.com.almavivasolutions.integrador.utils.pdf;

import java.util.List;

import br.com.almavivasolutions.integrador.utils.report.CertificateNotInsertedInfo;

public class PdfObject {
	private String content;
	private String documentTitle;
	private String tableTitle;
	private String[] tableHeaders; 
	private List<CertificateNotInsertedInfo> tableContent; 

	public PdfObject(String content, String documentTitle, List<CertificateNotInsertedInfo> tableContent, String[] tableHeaders,
			String tableTitle) {
		this.content = content;
		this.documentTitle = documentTitle;
		this.tableContent = tableContent;
		this.tableHeaders = tableHeaders;
		this.tableTitle = tableTitle;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public List<CertificateNotInsertedInfo> getTableContent() {
		return tableContent;
	}
	public void setTableContent(List<CertificateNotInsertedInfo> tableContent) {
		this.tableContent = tableContent;
	}
	public String[] getTableHeaders() {
		return tableHeaders;
	}
	public void setTableHeaders(String[] tableHeaders) {
		this.tableHeaders = tableHeaders;
	}
	public String getTableTitle() {
		return tableTitle;
	}
	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}
	
	
}

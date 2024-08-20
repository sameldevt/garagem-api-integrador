package br.com.almavivasolutions.integrador.utils.email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import br.com.almavivasolutions.integrador.utils.dotenv.DotenvLoader;
import br.com.almavivasolutions.integrador.utils.parser.PdfParser;
import br.com.almavivasolutions.integrador.utils.pdf.PdfObject;

public class EmailSender {
	public static void sendEmail(String toAddress, String subject, PdfObject pdfObject) {
		byte[] pdfBytes = new PdfParser(pdfObject).parse().toByteArray();
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp-mail.outlook.com");
		properties.put("mail.smtp.port", "587");
		
		Authenticator auth = new Authenticator() {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(DotenvLoader.botEmail, DotenvLoader.botPass);
			}
		};
		
		Session session = Session.getInstance(properties, auth);
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(DotenvLoader.botEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(pdfObject.getContent());

			MimeBodyPart attachPart = new MimeBodyPart();
			DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
			attachPart.setDataHandler(new DataHandler(dataSource));
			attachPart.setFileName("relatorio_integrador_certificacoes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM:ss_dd-MM-yyyy"))+".pdf");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachPart);
			
			msg.setContent(multipart);
			
			Transport.send(msg);
			System.out.println("enviado");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
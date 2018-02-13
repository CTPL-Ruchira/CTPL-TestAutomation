package com.netChain2.utils.javaMailClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailClient {
	
	public String mail_host;
	public String mail_subject;
	public String mail_Body;
	public String mail_Sender;
	public String mail_recipient_to;
	public String mail_recipient_cc;
	public String mail_recipient_bcc;
	public String userName;
	public String password;
	
	
	public JavaMailClient(String mail_host, String userName, String password, String mail_subject, String mail_Body,String mail_Sender, String mail_recipient_to, String mail_recipient_cc, String mail_recipient_bcc) {
		this.mail_host=mail_host;
		this.mail_subject=mail_subject;
		this.mail_Body=mail_Body;
		this.mail_Sender=mail_Sender;
		this.mail_recipient_to=mail_recipient_to;
		this.mail_recipient_cc=mail_recipient_cc;
		this.mail_recipient_bcc=mail_recipient_bcc;
		this.userName=userName;
		this.password=password;
	}
	
	public void sendEmail() {
		
				Properties props = new Properties();
                 props.put("mail.smtp.host", mail_host);
			     props.put("mail.debug", "true");
			     Authenticator authenticator =  new Authenticator(){
			     	protected PasswordAuthentication getPasswordAuthentication(){
			     		return new PasswordAuthentication(userName, password);
			     	}
			     	};
			     
			     

				// Get a session
				Session session = Session.getInstance(props,authenticator);
				
				try {
				Transport bus = session.getTransport("smtp");
				bus.connect();
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(this.mail_Sender, false));
				
				msg.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(this.mail_recipient_to, false));
				
				if (this.mail_recipient_cc != null)
					msg.setRecipients(Message.RecipientType.CC,
							InternetAddress.parse(this.mail_recipient_cc, false));

				if (this.mail_recipient_bcc != null)
					msg.setRecipients(Message.RecipientType.BCC,
							InternetAddress.parse(this.mail_recipient_bcc, false));

				msg.setSubject(this.mail_subject);

				msg.setSentDate(new Date());
				
				

				// HTMLDataSource is an inner class
				msg.setDataHandler(new DataHandler(new HTMLDataSource(this.mail_Body)));
				msg.saveChanges();

				// finally send it away now!
				Transport.send(msg);
				bus.close();

				
				}
				catch(Exception e) {
					e.printStackTrace();
				}
	}
	
	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		// Return html string in an InputStream.
		// A new stream must be returned each time.
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("Null HTML");
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}
	
}

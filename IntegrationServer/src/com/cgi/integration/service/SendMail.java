package com.cgi.integration.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
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

import org.slf4j.LoggerFactory;

public class SendMail {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	public void sendMailMain(String fileName, String mimeType, String email) throws IOException {

		final String username = "encommpoc@gmail.com";
		final String password = "thunderhead";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			String bccList = "vighneswarakumar.pallenaga@cgi.com,sravanichowdary70@gmail.com,a.begum@cgi.com,manirockz945@gmail.com,govindnitr@gmail.com,ayeshabegum.work@gmail.com";
			String[] bccListArray = bccList.split(",");
			String ccList="vighneswarakumar.pallenaga@cgi.com";
			String[] ccListArray = ccList.split(",");
			List<String> cclist =Arrays.asList(ccListArray);
			List<String> bcclist =Arrays.asList(bccListArray);
			message.setRecipients(Message.RecipientType.TO, email);
			
			 if (ccListArray != null && Arrays.asList(ccListArray).size() > 0) {
		            final Address[] toCcAddress = new InternetAddress[Arrays.asList(ccListArray).size()];
		            for (int i = 0; i < toCcAddress.length; i++) {
		                toCcAddress[i] = new InternetAddress(cclist.get(i));
		            }
		            // sets the cc list for sending mail. the same way BCC list can also be added.
		          //  message.addRecipients(Message.RecipientType.CC, toCcAddress);
		        }
			
			 if (bccListArray != null && Arrays.asList(bccListArray).size() > 0) {
		            final Address[] tobCcAddress = new InternetAddress[Arrays.asList(bccListArray).size()];
		            for (int i = 0; i < tobCcAddress.length; i++) {
		                tobCcAddress[i] = new InternetAddress(bcclist.get(i));
		            }
		            // sets the cc list for sending mail. the same way BCC list can also be added.
		            //message.addRecipients(Message.RecipientType.BCC, tobCcAddress);
		        }
			message.setFrom(new InternetAddress("encommpoc@gmail.com"));
			// Set Subject: header field
			message.setSubject("This is the Subject Line!");
			// message.setContent("<h1>This is actual message</h1>", "text/html");
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("\"<h1>This is actual message</h1>\"", "utf-8", "html");
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.attachFile(new File(fileName), mimeType, null);
			multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	void send(String fileName, String mimeType, String email) throws IOException {

		SendMail sc = new SendMail();
		sc.send(fileName, "application/pdf", "encommpoc@outlook.com");
		/*
		 * final String username = "encommpoc@outlook.com"; final String password =
		 * "Nov@2018";
		 * 
		 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
		 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
		 * "smtp-mail.outlook.com"); props.put("mail.smtp.port", "587");
		 * props.put("mail.smtp.user", username); props.put("mail.smtp.pwd", password);
		 * 
		 * 
		 * Session session = Session.getInstance(props, null); session.setDebug(true);
		 * Message msg = new MimeMessage(session); msg.setFrom(new
		 * InternetAddress(User)); if (Objet != null) { msg.setSubject(Objet);
		 * 
		 * } msg.setText(Content); msg.setRecipient(Message.RecipientType.TO, new
		 * InternetAddress(emailId)); Transport transport =
		 * session.getTransport("smtp"); transport.connect("smtp.live.com", 587, User,
		 * Pwd); transport.sendMessage(msg, msg.getAllRecipients());
		 * System.out.println("Mail sent successfully at " + emailId);
		 * transport.close(); }
		 * 
		 * 
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication(username, password); } });
		 * 
		 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		 * protected PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication(username, password); } });
		 * 
		 * try {
		 * 
		 * Message message = new MimeMessage(session); message.setFrom(new
		 * InternetAddress("encommpoc@outlook.com"));
		 * message.setRecipients(Message.RecipientType.TO,
		 * InternetAddress.parse("encommpoc@gmail.com")); // Set Subject: header field
		 * message.setSubject("This is the Subject Line!");
		 * 
		 * // message.setContent("<h1>This is actual message</h1>", "text/html");
		 * Multipart multipart = new MimeMultipart();
		 * 
		 * message.setContent(multipart); MimeBodyPart messageBodyPart = new
		 * MimeBodyPart(); messageBodyPart.setText("<h1>This is actual message</h1>",
		 * "utf-8", "html"); multipart.addBodyPart(messageBodyPart);
		 * 
		 * MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		 * attachmentBodyPart.attachFile(new File(fileName), mimeType, null);
		 * multipart.addBodyPart(attachmentBodyPart); message.setContent(multipart);
		 * Transport.send(message);
		 * 
		 * System.out.println("Done");
		 * 
		 * Transport.send(message); System.out.println("Mail sent successfully at " +
		 * "encommpoc@outlook.com"); catch (MessagingException e) { throw new
		 * RuntimeException(e); } }
		 */
	}
}
package org.nic.pmusha.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	
	//private static final Logger logger = LoggerFactory.getLogger(OtpDAOImpl.class);
	
	public static boolean sendEmail(String emailAddress, String subject, String body) {

		if(emailAddress == null || emailAddress.equals("")) {

		//	logger.info("Email cannot be sent, as email id is blank.");
			return false;
		}

		try {

		//	logger.info("Setting up email configuration");

			//InputStream emailPropertiesIS = Util.class.getResourceAsStream("/email.properties");

			Properties emailProperties = new Properties();

			//emailProperties.load(emailPropertiesIS);

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "noreply-aishe@nic.in");//noreply-mshe@nic.in

			Session session = Session.getDefaultInstance(emailProperties);

			//session.setDebug(false);

			Message emailMessage = new MimeMessage(session);

			InternetAddress addressFrom = new InternetAddress(emailProperties.getProperty("mail.smtp.user"));
			emailMessage.setFrom(addressFrom);
		//	logger.info("To Recepient"+emailAddress);
			InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[1];
			addressTo[0] = new InternetAddress(emailAddress);
			emailMessage.setRecipients(Message.RecipientType.TO, addressTo);

			


			//emailMessage.setRecipients(Message.RecipientType.BCC, addressBCC);

			emailMessage.setSubject(subject);

			emailMessage.setContent(body, "text/html");

			emailMessage.saveChanges();
			
	

			String toAddressString = "";

			for(InternetAddress toAddress : addressTo) {

				toAddressString = toAddressString + ", " + toAddress.getAddress();
			}

		//	logger.info("Sending emails to " + toAddressString + " with subject as " + subject);

			Transport.send(emailMessage);

		//	logger.info("Emails sent successfully.");

			//transport.close();
		}
		catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return true;
	}
	
	
	public static boolean sendEmailWithAttachment(String emailAddress, String subject, String body, File file, String FileName) {

		if(emailAddress == null || emailAddress.equals("")) {

		//	logger.info("Email cannot be sent, as email id is blank.");
			return false;
		}

		try {

		//	logger.info("Setting up email configuration");

			//InputStream emailPropertiesIS = Util.class.getResourceAsStream("/email.properties");

			Properties emailProperties = new Properties();

			//emailProperties.load(emailPropertiesIS);

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "noreply-mshe@nic.in");

			Session session = Session.getDefaultInstance(emailProperties);

			//session.setDebug(false);

			Message emailMessage = new MimeMessage(session);

			InternetAddress addressFrom = new InternetAddress(emailProperties.getProperty("mail.smtp.user"));
			emailMessage.setFrom(addressFrom);
		//	logger.info("To Recepient"+emailAddress);
			InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[1];
			addressTo[0] = new InternetAddress(emailAddress);
			emailMessage.setRecipients(Message.RecipientType.TO, addressTo);

			


			//emailMessage.setRecipients(Message.RecipientType.BCC, addressBCC);

			emailMessage.setSubject(subject);

		//	emailMessage.setContent(body, "text/html");

			emailMessage.saveChanges();
			
			
			//for attachment	
			

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);


			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			//   String filename = "/tmp/test.txt";
			if(null!=file && file.exists())
			{
				System.out.println("file name"+file.getName());
				DataSource source = new FileDataSource(file);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(FileName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			emailMessage.setContent(multipart );
			
			

			String toAddressString = "";

			for(InternetAddress toAddress : addressTo) {

				toAddressString = toAddressString + ", " + toAddress.getAddress();
			}

		//	logger.info("Sending emails to " + toAddressString + " with subject as " + subject);

			Transport.send(emailMessage);

		//	logger.info("Emails sent successfully.");

			//transport.close();
		}
		catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return true;
	}	
	

	
	public static String sendEmailToUser(List<String> UserMasterEmailList, String subject, String body, String emailFrom, File file, String FileName,String txtCCEmail) {

		if(UserMasterEmailList == null || UserMasterEmailList.equals("")) {

		//	logger.info("In sendEmailToUser(). Email cannot be sent, as email id is blank.");
			return "0";
		}

		String response ="2";

		try {

			Properties emailProperties = new Properties();
			///logger.info("In sendEmailToUser(), setting up email configuration");

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "helpdesk-aishe@nic.in");


			List<InternetAddress> addressCCList = new ArrayList<InternetAddress>();
			List<InternetAddress> addressBCCList = new ArrayList<InternetAddress>();



			//			String [] countries = list.toArray(new String[list.size()]);

			InternetAddress addressFrom = new InternetAddress(emailFrom);

			InternetAddress[] addressCC = null ;
			InternetAddress[] addressBCC = null ;

			for(String emails : UserMasterEmailList)
			{
				addressBCCList.add(new InternetAddress(emails));
			}


			addressCCList.add(new InternetAddress(emailFrom));


			if(null!=txtCCEmail && txtCCEmail.length()>0)
			{

				System.out.println("adding addressCC :: in CC ");
				addressCCList.add(new InternetAddress(txtCCEmail));

			}

			String emailddressString = "";





			Session session = Session.getDefaultInstance(emailProperties);


			addressBCC = addressBCCList.toArray(new InternetAddress[addressBCCList.size()]);
			addressCC  = addressCCList.toArray(new InternetAddress[addressCCList.size()]);

			for(InternetAddress emailAddress : addressBCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}

			for(InternetAddress emailAddress : addressCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}


		//	logger.info("Sending emails to " + emailddressString + " with subject as " + subject);
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(addressFrom);

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, addressBCC);

			System.out.println("addressBCC ::"+addressBCC.toString());

			if(null!=addressCC && addressCC.length > 0)
			{
				System.out.println("sending addressCC :: in CC ");
				message.addRecipients( Message.RecipientType.CC, addressCC);
			}

			System.out.println("addressCC ::"+addressCC.toString());

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);



			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			//   String filename = "/tmp/test.txt";
			if(null!=file && file.exists())
			{
				System.out.println("file name"+file.getName());
				DataSource source = new FileDataSource(file);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(FileName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart );

			// Send message
			//	Transport.send(message);
			//logger.info("Emails sent successfully.");
		}
		catch (Exception e) {

		//	logger.info("There is some problem in sending email, Please contact System Administartor");
		//	logger.debug("There is some problem in sending email, Please contact System Administartor");

			e.printStackTrace();
			response ="1";
		}

		return response;
	}
	public static void sendEmails(String emailAddress, String subject, String body) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "relay.nic.in");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.debug", "true");
		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		Transport transport = mailSession.getTransport();
		String msg = body;
		MimeMessage message = new MimeMessage(mailSession);
		message.setContent(msg, "text/html; charset=utf-8");
		message.setSubject(subject);
		message.setFrom(new InternetAddress("noreply-aishe@nic.in")); // from address
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
		transport.connect();
		transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}
	
	public static void sendForgotPasswordLinkInMail(String emailAddress, String subject, String body) {
		//final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 Properties props = System.getProperties();
		 props.put("mail.transport.protocol", "smtp");
		 props.setProperty("mail.smtp.host", "relay.nic.in");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 props.setProperty("mail.smtp.port", "25");
		 props.put("mail.debug", "true");
		
		 try{
		 Authenticator auth = new SMTPAuthenticator();
		 Session session = Session.getDefaultInstance(props,auth); 
		
		  Message msg = new MimeMessage(session);
		  msg.setContent(msg, "text/html; charset=utf-8");
		  msg.setFrom(new InternetAddress("noreply-aishe@nic.in"));
		  msg.setRecipients(Message.RecipientType.TO, 
		 InternetAddress.parse(emailAddress,false));
		  msg.setSubject("Ping");
		  msg.setText("To complete the password reset process, Your Otp is Here: "+body);
		 msg.setSentDate(new Date());
		 Transport.send(msg);
		 System.out.println("Message sent.");
		 }catch (MessagingException e){
			 System.out.println("Error Cause: " + e);}
		 }
	
	public static Boolean sendEmailstoMultipleUsers(String emailAddress, String subject, String body) throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "relay.nic.in");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.debug", "true");
		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		Transport transport = mailSession.getTransport();
		String msg = body;
		MimeMessage message = new MimeMessage(mailSession);
		message.setContent(msg, "text/html; charset=utf-8");
		message.setSubject(subject);
		message.setFrom(new InternetAddress("noreply-aishe@nic.in")); // from address
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
		transport.connect();
		transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
		transport.close();
		return true;
	}
	
	public static boolean sendEmailWithAttachmentRelay(String emailAddress, String subject, String body, File file, String FileName) {

		if(emailAddress == null || emailAddress.equals("")) {

			return false;
		}

		try {

			Properties emailProperties = new Properties();

			//emailProperties.load(emailPropertiesIS);

			emailProperties.put("mail.transport.protocol", "smtp");
			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.smtp.starttls.enable", "true");
			emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.debug", "true");
			//Session session = Session.getDefaultInstance(emailProperties);
			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(emailProperties, auth);
			Transport transport = mailSession.getTransport();
			String msg = body;
			MimeMessage message = new MimeMessage(mailSession);
			//message.setContent(msg, "text/html; charset=utf-8");
			message.setSubject(subject);
			message.setFrom(new InternetAddress("noreply-aishe@nic.in")); // from address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			//String msg = body;
			//MimeMessage emailMessage = new MimeMessage(mailSession);
			
			//Message emailMessage = new MimeMessage(session);

			//InternetAddress addressFrom = new InternetAddress(emailProperties.getProperty("mail.smtp.user"));
			//emailMessage.setFrom(addressFrom);
			//message.setContent(msg, "text/html; charset=utf-8");
			//message.setSubject(subject);
			//message.setFrom(new InternetAddress("noreply-aishe@nic.in")); // from address
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			
			
			
			InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[1];
			addressTo[0] = new InternetAddress(emailAddress);
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSubject(subject);
			message.saveChanges();
			
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);


			messageBodyPart = new MimeBodyPart();

			if(null!=file && file.exists())
			{
				System.out.println("file name"+file.getName());
				DataSource source = new FileDataSource(file);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(FileName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart );
		
			String toAddressString = "";

			for(InternetAddress toAddress : addressTo) {

				toAddressString = toAddressString + ", " + toAddress.getAddress();
			}
			transport.connect();
			transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
			transport.close();
			//Transport.send(emailMessage);

		}
		catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return true;
	}	
	
}

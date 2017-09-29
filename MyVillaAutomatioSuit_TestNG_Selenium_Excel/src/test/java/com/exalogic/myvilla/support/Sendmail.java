package com.exalogic.myvilla.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.exalogic.myvilla.util.SeleniumUtil;

public class Sendmail {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("SimpleEmail Start******************");
		//Sending mail Using smtp using ssl
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("callnirajgupta@gmail.com","ayushman1!");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("callnirajgupta@gmail.com"));
			message.setSubject("Testing Subject");
	
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText("Please find the attached Automation execution result of exalogic");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = System.getProperty("user.dir")+"//test-output//html//index.html";
	        WebDriver driver=SeleniumUtil.getInstance().getDriver();//if u need  to it..
	 		SeleniumUtil.maximizeBrowser();
	 		SeleniumUtil.ImplicitWait();		
	 		SeleniumUtil.NavigateTo(filename);
	 		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//ScreenShot//Result.png"));
			SeleniumUtil.closeBrowser();
	 		
			String filename1=System.getProperty("user.dir") + "//ScreenShot//Result.png";
	         DataSource source = new FileDataSource(filename1);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename1);
	         multipart.addBodyPart(messageBodyPart);
	         // Send the complete message parts
	         message.setContent(multipart );

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		}	
}

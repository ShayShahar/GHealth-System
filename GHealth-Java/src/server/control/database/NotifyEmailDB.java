package server.control.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import common.entity.Request;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotifyEmailDB {

	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;

	
 public static Object handleMessage (Request request, Connection connection) {

	
	String findEmails = "SELECT person.personName, person.personEmail FROM ghealth.appointments, ghealth.clients, ghealth.person " +
													"WHERE dayofyear(appointments.appDate) = dayofyear(current_date()) + 1 AND appointments.client = clients.clientID AND " +
													"clients.person = person.personID AND person.personEmail <> 'none'";
	
	String updateDate = "UPDATE notifications SET lastUpdate = curdate() LIMIT 1";
	
	String checkLastDate = "SELECT * FROM ghealth.notifications WHERE curdate() > lastUpdate";
	
	
	try{
		
		PreparedStatement preparedStatement0 = connection.prepareStatement(checkLastDate);    				
		ResultSet result0 = preparedStatement0.executeQuery();
		
		if (result0.next())
		{
				PreparedStatement preparedStatement = connection.prepareStatement(findEmails);    				
				ResultSet result = preparedStatement.executeQuery();
				
				while (result.next()){
					
					try{
						generateAndSendEmail(result.getString(2), result.getString(1));
		
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
				}
				PreparedStatement preparedStatement2 = connection.prepareStatement(updateDate);    				
				preparedStatement2.executeUpdate();
		}
		
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	
	return null;
	
 }
 
 public static void generateAndSendEmail(String recipent, String name) throws AddressException, MessagingException {
	 
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
		generateMailMessage.setSubject("GHealth System Appointment Reminder");
		String emailBody = "Dear " + name + ", \nwe want to remind you that tomorrow you have an appointment at IHealth. \n<br><br> Regards, <br>IHealth Network";
		generateMailMessage.setContent(emailBody, "text/html");

		Transport transport = getMailSession.getTransport("smtp");

		transport.connect("smtp.gmail.com", "ghealth.system", "GHEALTH1234");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	} 
 
}

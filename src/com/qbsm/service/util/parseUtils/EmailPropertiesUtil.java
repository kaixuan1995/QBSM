package com.qbsm.service.util.parseUtils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.qbsm.service.exception.ParseException;
import com.qbsm.util.PropertiseUtil;

@SuppressWarnings("all")
public class EmailPropertiesUtil {

	private static String fileName = "/Email.properties";
	private static Properties properties = null;
	private static String smtp;
	private static String port;
	private static String auth;
	
	private static String from;
	private static String subject;
	private static String content;

	private static class ResourceHolder {
		public static EmailPropertiesUtil emailPropertiesUtil = new EmailPropertiesUtil();
	}

	private EmailPropertiesUtil() {
	}
	
	protected class Email_Authenticator extends Authenticator {
		private String username;
		private String password;
		public Email_Authenticator() {
			this.username = PropertiseUtil.getKeyIsValue(properties, "username");
			this.password = PropertiseUtil.getKeyIsValue(properties, "password");
			System.out.println(password);
		}
		
		 protected PasswordAuthentication getPasswordAuthentication() {
		     return new PasswordAuthentication(username, password);
		 }
	}
	
	public Email_Authenticator getEmail_Authenticator(){
		return new Email_Authenticator();
	}

	public static EmailPropertiesUtil getInstance() throws ParseException {
		properties = PropertiseUtil.getProperties(fileName);
		
		properties.put("mail.smtp.host", PropertiseUtil.getKeyIsValue(properties, "host"));
	    properties.put("mail.smtp.port", PropertiseUtil.getKeyIsValue(properties, "port"));
	    properties.put("mail.smtp.auth", PropertiseUtil.getKeyIsValue(properties, "auth"));
	    properties.put("mail.smtp.socketFactory.class", PropertiseUtil.getKeyIsValue(properties, "socketFactory.class"));
	    properties.put("mail.smtp.socketFactory.port", PropertiseUtil.getKeyIsValue(properties, "socketFactory.port"));
	    
	    from = PropertiseUtil.getKeyIsValue(properties, "from");
		subject = PropertiseUtil.getKeyIsValue(properties, "subject");
		content = PropertiseUtil.getKeyIsValue(properties, "content");
		return EmailPropertiesUtil.ResourceHolder.emailPropertiesUtil;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

}

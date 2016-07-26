package com.qbsm.service.util;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.qbsm.service.exception.ParseException;
import com.qbsm.service.util.parseUtils.EmailPropertiesUtil;

public class EmailUtil {
	
	private static EmailPropertiesUtil emailPropertiesUtil2;

    public static void main(String args[]){
        try {
            send_email("123456","819537628@qq.com");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean send_email(String userCode,String email){
    	try {
			emailPropertiesUtil2 = EmailPropertiesUtil.getInstance();
			String subject = emailPropertiesUtil2.getSubject();//邮件主题
			String content = emailPropertiesUtil2.getContent();//邮件内容
			
			content = content.replace("userCode", userCode);  //转换邮件内容
			
			Properties properties = emailPropertiesUtil2.getProperties();
			Authenticator authenticator = emailPropertiesUtil2.getEmail_Authenticator();
			
			Session sendMailSession = Session.getDefaultInstance(properties,authenticator);
			
			MimeMessage mailMessage = new MimeMessage(sendMailSession);
			mailMessage.setFrom(new InternetAddress(emailPropertiesUtil2.getFrom()));
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			mailMessage.setSubject(subject, "UTF-8");
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			html.setContent(content.trim(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			
			Transport.send(mailMessage);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
    }
}
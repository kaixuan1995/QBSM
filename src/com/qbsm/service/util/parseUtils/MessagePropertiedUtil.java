package com.qbsm.service.util.parseUtils;

import java.util.Properties;

import com.qbsm.service.exception.ParseException;
import com.qbsm.util.PropertiseUtil;


public class MessagePropertiedUtil {

	private static String fileName = "/Message.properties";
	
	private  MessagePropertiedUtil(){};
	
	private static class ResourceHolder {
		public static MessagePropertiedUtil messagePropertiesUtil = new MessagePropertiedUtil();
	}
	
	public static MessagePropertiedUtil getInstance() {
		return MessagePropertiedUtil.ResourceHolder.messagePropertiesUtil;
	}
	
	private Properties getProperties() throws ParseException{
		return PropertiseUtil.getProperties(fileName);
	}
	
	public String getMessage(String key) throws ParseException {
		return PropertiseUtil.getKeyIsValue(getProperties(), key);
	}

}

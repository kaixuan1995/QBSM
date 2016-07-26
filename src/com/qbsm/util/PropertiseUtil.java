package com.qbsm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qbsm.service.exception.ParseException;



public class PropertiseUtil {
	
	public static Properties getProperties(String fileName) throws ParseException {
		Properties propertise = new Properties();
		InputStream is = PropertiseUtil.class.getResourceAsStream(fileName);
		try {
			propertise.load(is);
		} catch (IOException e) {
			throw new ParseException("没有找到名为"+fileName+"的properties文件");
		}
		return propertise;
	}
	
	public static String getKeyIsValue(Properties properties,String key){
		return properties.getProperty(key);
	}
}

package com.qbsm.service.util;

import com.qbsm.service.exception.ParseException;
import com.qbsm.service.util.parseUtils.MessagePropertiedUtil;

public class MessageUtil {
	
	//得到系统提示的信息
	public static String getMessage(String key){
		try {
			return MessagePropertiedUtil.getInstance().getMessage(key);
		} catch (ParseException e) {
			e.printStackTrace();
			return "系统繁忙，请稍后再试";
		}
	}
}

package com.qbsm.service.util.parseUtils;


import org.dom4j.Document;

import com.qbsm.util.XmlUtils;

public class ServiceXMLUtil {

	private static String fileName = "service.xml";

	public ServiceXMLUtil(String fileName) {
		super();
		ServiceXMLUtil.fileName = fileName;
	}

	public ServiceXMLUtil() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		ServiceXMLUtil.fileName = fileName;
	}

	//保证得到的document单例模式
	private static class ResouceHolder {
		public static Document document = XmlUtils.getDocument(fileName);
	}
	
	private static Document getInstance() {
		return ServiceXMLUtil.ResouceHolder.document;
	}
	
	public static String getServiceImplName(String serviceName){
		return XmlUtils.getNodeContextName(getInstance(),"serviceName",
				serviceName, "serviceImplName");
	}

	
	public static String getMethodName(String serviceName){
		return XmlUtils.getNodeContextName(getInstance(),"serviceName",
				serviceName, "MethodName");
	}
	
	public static String getArgs(String serviceName){
		return XmlUtils.getNodeContextName(getInstance(),"serviceName",
				serviceName, "args");
	}
}

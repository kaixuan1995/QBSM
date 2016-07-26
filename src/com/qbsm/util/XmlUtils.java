package com.qbsm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


@SuppressWarnings("all")
public class XmlUtils {
	
	// 得到指定xml文件的decument对象
	public static Document getDocument(String fileName) {
		
		URL url = XmlUtils.class.getClassLoader().getResource(fileName);
		String realpath = url.getPath();
		SAXReader reader = new SAXReader();
		try {
			return reader.read(new File(realpath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到id的值与rootId相同的某个节点的值
	 * 
	 * @param document
	 *            ：某个xml的document对象
	 * @param rootName
	 *            ：根节点的名称
	 * @param rootId
	 *            ：id名称
	 * @param nodeName
	 *            ：节点名称
	 * @return
	 */
	public static String getNodeContextName(Document document, String rootName,
			String rootId, String nodeName) {
		Element root = document.getRootElement();
		List<Element> list = root.elements(rootName);
		
		for (Element element : list) {
			if (rootId.equals(element.attribute("id").getValue())) {
				return element.elementText(nodeName);
			}
		}
		return null;
	}

	// 将内存中的document写入xml文件中
	private static void write2Xml(String fileName, Document document)
			throws IOException {

		URL url = XmlUtils.class.getClassLoader().getResource(fileName);
		String realpath = url.getPath();

		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileOutputStream(realpath), format);
		writer.write(document);
		writer.close();
	}

}

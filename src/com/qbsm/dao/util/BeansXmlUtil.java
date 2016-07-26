package com.qbsm.dao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


@SuppressWarnings("all")
public class BeansXmlUtil {

	private static Map<String, VoResolve> voMap = new HashMap<String, VoResolve>();
	
	private static BeansXmlUtil beansXmlUtil = new BeansXmlUtil();
	// xml配置文件的路径
	private final String xmlPath = this.getClass().getClassLoader().getResource("\\").getPath() + "gladiolus-beans.xml";

	private BeansXmlUtil() {
		getVoResolveByXml(xmlPath);
	}

	public static BeansXmlUtil getBeansXmlUtil() {
		return beansXmlUtil;
	}

	/**
	 * 获得所有的Vo对应的Po
	 * 
	 * @param path
	 * @return
	 */
	private Map<String, VoResolve> getVoResolveByXml(String path) {
		Document document = getDocument(path);
		// 获取根节点
		Element root = document.getRootElement();
		// 获取指定名称的所有节点
		List<Element> voElements = root.elements("vo");
		try {
			for (int i = 0; i < voElements.size(); i++) {
				VoResolve vo = new VoResolve();
				List<Class> pos = new ArrayList<Class>();
				Map<Integer, String> temp = new HashMap<Integer, String>();
				List<Element> poElements = voElements.get(i).elements("po");
				for (int j = 0; j < poElements.size(); j++) {
					String poClass = poElements.get(j).attributeValue("class");
					String poOrder = poElements.get(j).attributeValue("order");
					temp.put(Integer.parseInt(poOrder), poClass);
				}
				for (int k = 1; k <= poElements.size(); k++) {
					pos.add(Class.forName(temp.get(k)));
				}
				vo.setVoName(voElements.get(i).attributeValue("id"));
				vo.setVoClass(voElements.get(i).attributeValue("class"));
				vo.setPos(pos);
				voMap.put(voElements.get(i).attributeValue("id").toLowerCase(), vo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return voMap;
	}

	public VoResolve getVoResolve(String voName) {
		return voMap.get(voName.trim().toLowerCase());
	}

	/**
	 * 获得文档对象
	 * 
	 * @param path
	 * @return
	 */
	private Document getDocument(String path) {
		// 实例化SAXReader对象
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 获取XML文件对应的XML文档对象
			document = reader.read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
}

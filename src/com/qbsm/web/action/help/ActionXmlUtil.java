package com.qbsm.web.action.help;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析Kaiser-action.xml配置文件中的Action节点
 * 
 * @author xieguoping
 * 
 */
public class ActionXmlUtil {

	@SuppressWarnings("unchecked")
	public static XmlActionMappings parsexml(String xmlFileURL) {
		XmlActionMappings xmlActionMappings = new XmlActionMappings();
		ActionMapping actionMapping = null;
		// 实例化SAXReader对象
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 获取XML文件对应的XML文档对象
			document = reader.read(xmlFileURL);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 获取根节点
		Element palcard = document.getRootElement();
		// 获取指定名称的所有节点
		List<Element> actionElements = palcard.elements("action");
		for (int i = 0; i < actionElements.size(); i++) {
			actionMapping = new ActionMapping();
			Element ele = actionElements.get(i);
			String actionName = ele.attributeValue("name");
			String actionClass = ele.attributeValue("class");
			actionMapping.setActionName(actionName);
			actionMapping.setActionClass(actionClass);

			List<Element> resultElements = ele.elements("result");
			for (int j = 0; j < resultElements.size(); j++) {
				Element resultElement = resultElements.get(j);
				String result = resultElement.attributeValue("name");
				actionMapping.addMapElement(result, resultElement.getText());
			}
			xmlActionMappings.addActionMapping(actionMapping);
		}
		return xmlActionMappings;
	}
}

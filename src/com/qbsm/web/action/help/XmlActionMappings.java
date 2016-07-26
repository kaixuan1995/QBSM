package com.qbsm.web.action.help;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * kaiser-action.xml中所有的Action映射的集合类
 * 
 * @author xieguoping
 * 
 */
public class XmlActionMappings {
	
	private Map<String, ActionMapping> xmlActionMappings = new HashMap<String, ActionMapping>();

	public Map<String, ActionMapping> getXmlActionMappings() {
		return xmlActionMappings;
	}

	public void setXmlActionMappings(
			Map<String, ActionMapping> xmlActionMappings) {
		this.xmlActionMappings = xmlActionMappings;
	}

	public Map<String, ActionMapping> addActionMapping(
			ActionMapping actionMapping) {
		xmlActionMappings.put(actionMapping.getActionName(), actionMapping);
		return xmlActionMappings;
	}

	/**
	 * 获得action对应的xml映射对象,先找完全匹配的，如果没有完全与action匹配的映射对象则正则模糊匹配
	 * 
	 * @param action
	 * @return
	 */
	public ActionMapping getActionMapping(String action) {

		Iterator<String> it = this.xmlActionMappings.keySet().iterator();

		// 寻找与action完全匹配的映射集合
		while (it.hasNext()) {
			String key = it.next();
			if (action.equalsIgnoreCase(key)) {
				return this.xmlActionMappings.get(key);
			}
		}

		// 寻找与action模糊匹配的映射集合
		Object[] array = this.xmlActionMappings.keySet().toArray();
		for (int i = array.length - 1; i >= 0; i--) {
			System.out.println("tem-->" + array[i].toString());
			String key = array[i].toString();
			if (action.matches(key)) {
				return this.xmlActionMappings.get(key);
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "XmlActionMappings [xmlActionMappings=" + xmlActionMappings
				+ "]";
	}
}

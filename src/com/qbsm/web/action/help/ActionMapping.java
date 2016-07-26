package com.qbsm.web.action.help;

import java.util.HashMap;
import java.util.Map;

/**
 * kaiser-action.xml中单个action的映射
 * @author xieguoping
 *
 */
public class ActionMapping {
	
	private String operate;
	
	private String actionName;
	private String actionClass;
	private Map<String, String> resultMapping = new HashMap<String, String>();

	public Map<String, String> addMapElement(String key, String value) {
		resultMapping.put(key, value);
		return this.resultMapping;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public Map<String, String> getResultMapping() {
		return resultMapping;
	}

	public void setResultMapping(Map<String, String> resultMapping) {
		this.resultMapping = resultMapping;
	}

	@Override
	public String toString() {
		return "ActionMapping [operate=" + operate + ", actionName="
				+ actionName + ", actionClass=" + actionClass
				+ ", resultMapping=" + resultMapping + "]";
	}

}

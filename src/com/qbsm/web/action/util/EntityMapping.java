package com.qbsm.web.action.util;

public class EntityMapping {
	private String name;
	private String className;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "EntityMapping [name=" + name + ", className=" + className + "]";
	}
}

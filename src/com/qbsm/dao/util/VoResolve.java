package com.qbsm.dao.util;

import java.util.List;


@SuppressWarnings("all")
public class VoResolve {
	
	private String voName;
	private String voClass;
	private List<Class> pos;

	public String getVoClass() {
		return voClass;
	}

	public void setVoClass(String voClass) {
		this.voClass = voClass;
	}

	public List<Class> getPos() {
		return pos;
	}

	public void setPos(List<Class> pos) {
		this.pos = pos;
	}
	public String getVoName() {
		return voName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}
	@Override
	public String toString() {
		return "VoResolve [voName=" + voName + ", voClass=" + voClass + ", pos=" + pos + "]";
	}
}

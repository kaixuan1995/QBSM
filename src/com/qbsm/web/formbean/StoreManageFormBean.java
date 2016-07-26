package com.qbsm.web.formbean;

/**
 * 仓库管理视图
 */
public class StoreManageFormBean {
	private Integer storehouse_id;			//仓库编号
	private String storehouse_name;		//仓库名称
	public Integer getStorehouse_id() {
		return storehouse_id;
	}
	public void setStorehouse_id(Integer storehouse_id) {
		this.storehouse_id = storehouse_id;
	}
	public String getStorehouse_name() {
		return storehouse_name;
	}
	public void setStorehouse_name(String storehouse_name) {
		this.storehouse_name = storehouse_name;
	}
	
}

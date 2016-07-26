package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_storehouse")
public class Storehouse {

	// 仓库表
	@PrimaryColumn("storehouse_id")
	private Integer storehouse_id; // 表id

	private String storehouse_name; // 仓库名称
	private Integer storehouse_isdel; //
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
	public Integer getStorehouse_isdel() {
		return storehouse_isdel;
	}
	public void setStorehouse_isdel(Integer storehouse_isdel) {
		this.storehouse_isdel = storehouse_isdel;
	}
}
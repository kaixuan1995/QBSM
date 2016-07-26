package com.qbsm.bean;


import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_role")
public class Role {

	// 角色表
	@PrimaryColumn("role_id")
	private Integer role_id; // 表id

	private String role_name; // 角色名称
	private String role_creater; // 创建人
	private String role_createdata; // 创建时间
	private Integer role_isdel; //是否物理删除

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_creater() {
		return role_creater;
	}

	public void setRole_creater(String role_creater) {
		this.role_creater = role_creater;
	}

	public Integer getRole_isdel() {
		return role_isdel;
	}

	public void setRole_isdel(Integer role_isdel) {
		this.role_isdel = role_isdel;
	}

	public String getRole_createdata() {
		return role_createdata;
	}

	public void setRole_createdata(String role_createdata) {
		this.role_createdata = role_createdata;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name
				+ ", role_creater=" + role_creater + ", role_createdata="
				+ role_createdata + ", role_isdel=" + role_isdel + "]";
	}

}
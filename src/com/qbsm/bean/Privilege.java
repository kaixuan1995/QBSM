package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//权限表
@Table("t_privilege")
public class Privilege {
	@PrimaryColumn("privilege_id")
	private Integer privilege_id; // 权限id

	private Integer privilege_rootnode; // 根权限
	private String privilege_name; // 权限名称
	private String privilege_path; // 权限路径
	private Integer privilege_node; // 父权限
	private String privilege_isroort; // 是否是根节点
	private Integer privilege_isdel; //

	public Integer getPrivilege_id() {
		return privilege_id;
	}

	public void setPrivilege_id(Integer privilege_id) {
		this.privilege_id = privilege_id;
	}

	public Integer getPrivilege_rootnode() {
		return privilege_rootnode;
	}

	public void setPrivilege_rootnode(Integer privilege_rootnode) {
		this.privilege_rootnode = privilege_rootnode;
	}

	public String getPrivilege_name() {
		return privilege_name;
	}

	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}

	public String getPrivilege_path() {
		return privilege_path;
	}

	public void setPrivilege_path(String privilege_path) {
		this.privilege_path = privilege_path;
	}

	public Integer getPrivilege_node() {
		return privilege_node;
	}

	public void setPrivilege_node(Integer privilege_node) {
		this.privilege_node = privilege_node;
	}

	public String getPrivilege_isroort() {
		return privilege_isroort;
	}

	public void setPrivilege_isroort(String privilege_isroort) {
		this.privilege_isroort = privilege_isroort;
	}

	public Integer getPrivilege_isdel() {
		return privilege_isdel;
	}

	public void setPrivilege_isdel(Integer privilege_isdel) {
		this.privilege_isdel = privilege_isdel;
	}

}
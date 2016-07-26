package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_role_privilege")
public class Role_Privilege {

	@PrimaryColumn("role_privilege_id")
	private Integer role_privilege_id; // id

	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer user_id_fk; // 用户id

	@ForeignColumn(ColumnName = "t_privilege_id", Table = "t_privilege")
	private Integer privilege_id_fk; // 权限id

	private Integer role_privilege_isdel; //

	public Integer getRole_privilege_id() {
		return role_privilege_id;
	}

	public void setRole_privilege_id(Integer role_privilege_id) {
		this.role_privilege_id = role_privilege_id;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Integer getPrivilege_id_fk() {
		return privilege_id_fk;
	}

	public void setPrivilege_id_fk(Integer privilege_id_fk) {
		this.privilege_id_fk = privilege_id_fk;
	}

	public Integer getRole_privilege_isdel() {
		return role_privilege_isdel;
	}

	public void setRole_privilege_isdel(Integer role_privilege_isdel) {
		this.role_privilege_isdel = role_privilege_isdel;
	}

}
package com.qbsm.web.formbean;


/**
 * 个人信息视图
 * 以及供应商信息申请
 */
//设计的物理表有：用户表、
public class UserFormBean {
	
//关联User
	private String user_id;					//用户id

//关联t_office
//通过t_user.office_id_fk = t_office.office.id 查询到t_office.office_name
	private String office_name;				//所属科室
	
//关联t_role
//关联通过t_user.user_id = t_role.user_id_fk 查询到t_role.role_name
	private String role_name;				//角色
	
	private String user_name;				//用户姓名/供应商姓名
	private String user_email;				//电子邮件
	private String user_address;			//地址
	private String user_lianxiren;			//联系人
	private String user_phone;				//手机号
	private String user_fax;				//传真号
	private String user_createtime;			//用户创建时间
	private String user_lastlogintime;		//最后登录时间
	private String user_lastloginip;		//最后登录IP
	
//先找Supplier_Goodstype表找到type_id_fk,然后再找type表找到type_name
	private String type_name;				//供应类别
	
	private String user_evaluate;			//评价：注意，不是数据库里的字段
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getUser_lianxiren() {
		return user_lianxiren;
	}
	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_fax() {
		return user_fax;
	}
	public void setUser_fax(String user_fax) {
		this.user_fax = user_fax;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_createtime() {
		return user_createtime;
	}
	public void setUser_createtime(String user_createtime) {
		this.user_createtime = user_createtime;
	}
	public String getUser_lastlogintime() {
		return user_lastlogintime;
	}
	public void setUser_lastlogintime(String user_lastlogintime) {
		this.user_lastlogintime = user_lastlogintime;
	}
	public String getUser_lastloginip() {
		return user_lastloginip;
	}
	public void setUser_lastloginip(String user_lastloginip) {
		this.user_lastloginip = user_lastloginip;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getUser_evaluate() {
		return user_evaluate;
	}
	public void setUser_evaluate(String user_evaluate) {
		this.user_evaluate = user_evaluate;
	}
	@Override
	public String toString() {
		return "UserFormBean [user_id=" + user_id + ", office_name="
				+ office_name + ", role_name=" + role_name + ", user_name="
				+ user_name + ", user_email=" + user_email + ", user_address="
				+ user_address + ", user_lianxiren=" + user_lianxiren
				+ ", user_phone=" + user_phone + ", user_fax=" + user_fax
				+ ", user_createtime=" + user_createtime
				+ ", user_lastlogintime=" + user_lastlogintime
				+ ", user_lastloginip=" + user_lastloginip + ", type_name="
				+ type_name + ", user_evaluate=" + user_evaluate + "]";
	}
	
	
	
}

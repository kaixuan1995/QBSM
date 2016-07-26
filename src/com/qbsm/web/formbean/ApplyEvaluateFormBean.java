package com.qbsm.web.formbean;

/**
 * 供应商评价视图
 */
//设计的物理表有：用户表、applyPicture、apply表
public class ApplyEvaluateFormBean {
	
//关联user表
	private String user_name;				//供应商
	private String user_address;			//地址
	private String user_phone;				//电话
	
//关联apply表
	private Integer apply_id;				//申请订单id
	private String apply_time;				//时间：为完成订单，生成凭证单的时间
//	private String evaluate;				//好评率:非数据库字段
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public Integer getApply_id() {
		return apply_id;
	}
	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	
//	public String getEvaluate() {
//		return evaluate;
//	}
//	public void setEvaluate(String evaluate) {
//		this.evaluate = evaluate;
//	}
	
	
}

package com.qbsm.web.formbean;

/**
 * 单据管理视图
 */
public class ApplyPictureFormBean {
	
//
	private Integer apply_picture_id;		//单据编号
	private String user_name;				//经手人
	private String place_name;				//涉及仓库
	private String supplier;				//供应商--对应物资的供应商姓名
	private String apply_picture_time;		//生成时间
	
	public String getApply_picture_time() {
		return apply_picture_time;
	}
	public void setApply_picture_time(String apply_picture_time) {
		this.apply_picture_time = apply_picture_time;
	}
	public Integer getApply_picture_id() {
		return apply_picture_id;
	}
	public void setApply_picture_id(Integer apply_picture_id) {
		this.apply_picture_id = apply_picture_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
}

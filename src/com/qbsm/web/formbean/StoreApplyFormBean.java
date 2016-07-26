package com.qbsm.web.formbean;

/**
 * 申请视图(进出库申请等) 检测人员：查看我的申请视图
 */
// 涉及到申请单总表、用户表、仓库表、仓库分区表
public class StoreApplyFormBean {

	// 关联t_apply
	private Integer apply_id; // 申请单号
	private String apply_time; // 申请时间
	private String apply_urgent; // 是否加急
	private String apply_remark; // 是否加急
	private String apply_state; // 状态:待审核，审核通过    审核失败，采购中   已到货   已入库    已领用
	private String apply_type; // 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货

	// 关联t_storehouse
	private String storehouse_name; // 涉及仓库
	
	private String place_id;		//仓库分区id

	// 关联t_user
	private String user_lianxiren; // 申请人

	//关联 t_office
	private String office_name;
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
	
	public String getApply_urgent() {
		return apply_urgent;
	}

	public void setApply_urgent(String apply_urgent) {
		this.apply_urgent = apply_urgent;
	}

	public String getApply_state() {
		return apply_state;
	}

	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getStorehouse_name() {
		return storehouse_name;
	}

	public void setStorehouse_name(String storehouse_name) {
		this.storehouse_name = storehouse_name;
	}

	public String getUser_lianxiren() {
		return user_lianxiren;
	}

	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}
	
	

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getApply_remark() {
		return apply_remark;
	}

	public void setApply_remark(String apply_remark) {
		this.apply_remark = apply_remark;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	@Override
	public String toString() {
		return "StoreApplyFormBean [apply_id=" + apply_id + ", apply_time="
				+ apply_time + ", apply_urgent=" + apply_urgent
				+ ", apply_remark=" + apply_remark + ", apply_state="
				+ apply_state + ", apply_type=" + apply_type
				+ ", storehouse_name=" + storehouse_name + ", place_id="
				+ place_id + ", user_lianxiren=" + user_lianxiren
				+ ", office_name=" + office_name + "]";
	}


	

}

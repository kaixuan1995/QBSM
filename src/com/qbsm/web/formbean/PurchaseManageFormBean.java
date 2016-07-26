package com.qbsm.web.formbean;

/**
 * 采购计划视图
 */
//设计的物理表：物资表、物资类别表、用户表、科室表、申请物资信息详细表、申请表
public class PurchaseManageFormBean {
	
	
//关联t_apply
	private Integer apply_id;			//申请单号
	private String apply_urgent;		//是否加急
	private String apply_state; 			// 申请时间
	public String getApply_state() {
		return apply_state;
	}
	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}
	private String apply_time; 			// 申请时间
	
//关联t_goods
	private Integer goods_id;			//物资编号
	private String  goods_name;			//物资名称
	private String  goods_brand;		//物资品牌
	private String  goods_unit;			//单位
	private String  goods_standard;		//规格
	private String  goods_cas;			//货号

//关联t_type	
	private String type_name;		//物资类别
	
//关联t_user
	private String user_lianxiren;		//供应商
	
//关联t_office
	private String office_name;			//所属科室
	
//关联t_apply_goods
	private Integer apply_goods_count;				//申请数量：此处是以科室为单位计算整个数量
	
	//关联t_apply_goods
	private Integer voucher_goods_count;				//申请数量：此处是以科室为单位计算整个数量
	

	public Integer getVoucher_goods_count() {
			return voucher_goods_count;
		}
		public void setVoucher_goods_count(Integer voucher_goods_count) {
			this.voucher_goods_count = voucher_goods_count;
		}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_brand() {
		return goods_brand;
	}
	public void setGoods_brand(String goods_brand) {
		this.goods_brand = goods_brand;
	}
	public String getGoods_unit() {
		return goods_unit;
	}
	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}
	public String getGoods_standard() {
		return goods_standard;
	}
	public void setGoods_standard(String goods_standard) {
		this.goods_standard = goods_standard;
	}
	public String getGoods_cas() {
		return goods_cas;
	}
	public void setGoods_cas(String goods_cas) {
		this.goods_cas = goods_cas;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getUser_lianxiren() {
		return user_lianxiren;
	}
	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public Integer getApply_goods_count() {
		return apply_goods_count;
	}
	public void setApply_goods_count(Integer apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}
	public Integer getApply_id() {
		return apply_id;
	}
	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}
	public String getApply_urgent() {
		return apply_urgent;
	}
	public void setApply_urgent(String apply_urgent) {
		this.apply_urgent = apply_urgent;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	@Override
	public String toString() {
		return "PurchaseManageFormBean [apply_id=" + apply_id
				+ ", apply_urgent=" + apply_urgent + ", apply_state="
				+ apply_state + ", apply_time=" + apply_time + ", goods_id="
				+ goods_id + ", goods_name=" + goods_name + ", goods_brand="
				+ goods_brand + ", goods_unit=" + goods_unit
				+ ", goods_standard=" + goods_standard + ", goods_cas="
				+ goods_cas + ", type_name=" + type_name + ", user_lianxiren="
				+ user_lianxiren + ", office_name=" + office_name
				+ ", apply_goods_count=" + apply_goods_count
				+ ", voucher_goods_count=" + voucher_goods_count + "]";
	}
	
}

package com.qbsm.web.formbean;


/**
 * 物资申请详细视图
 * 申请采购、领用、退用、退货等视图
 */
public class StoreApplyDFormBean {
	
//关联goods表--->t_goods
	private Integer goods_id;				//物资编号
	private String goods_name;				//物资名称
	private String goods_brand;				//物资品牌
	private String goods_unit;				//单位
	private String goods_standard;			//规格
	private String goods_cas;				//货号
	
//关联apply--->t_apply
	private Integer apply_id;				//申请编号
	private String apply_time;				//申请时间
	private String apply_state;				//申请状态
	
//关联type--->t_type
	private String type_name;				//物资类别
	
//关联apply_goods-->t_apply_goods
	private String apply_goods_count;		//申请数量
	
//t_user
	private Integer user_id_fk;
	private String user_lianxiren; 			// 供应商的联系人
	
//t_barcode有效期
	private String barcode_validity;//有效期

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

	public String getApply_state() {
		return apply_state;
	}

	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getApply_goods_count() {
		return apply_goods_count;
	}

	public void setApply_goods_count(String apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getUser_lianxiren() {
		return user_lianxiren;
	}

	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}

	public String getBarcode_validity() {
		return barcode_validity;
	}

	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
	}

	@Override
	public String toString() {
		return "StoreApplyDFormBean [goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand + ", goods_unit="
				+ goods_unit + ", goods_standard=" + goods_standard
				+ ", goods_cas=" + goods_cas + ", apply_id=" + apply_id
				+ ", apply_time=" + apply_time + ", apply_state=" + apply_state
				+ ", type_name=" + type_name + ", apply_goods_count="
				+ apply_goods_count + ", user_id_fk=" + user_id_fk
				+ ", user_lianxiren=" + user_lianxiren + ", barcode_validity="
				+ barcode_validity + "]";
	}
	
	
	
	
	
}

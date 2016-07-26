package com.qbsm.web.formbean;

/**
 * 查看申请入库的物资的详细信息视图
 * @author zkx
 *
 */
public class LookInStoreApplyFormBean {
	// 关联goods表--->t_goods
	private Integer goods_id; // 物资编号
	private String goods_name; // 物资名称
	private String goods_brand; // 物资品牌
	private String goods_unit; // 单位
	private String goods_standard; // 规格
	private String goods_cas; // 货号
	// 关联t_goods_type表--->
	private String type_name; // 物资类别
	// 关联t_ barcode
	private String barcode_validity; // 物资有效期
	// 关联t_apply_goods
	private int apply_goods_count; // 申请入库数量
	//关联t_apply
	private int apply_id_fk;

	public int getApply_id_fk() {
		return apply_id_fk;
	}

	public void setApply_id_fk(int apply_id_fk) {
		this.apply_id_fk = apply_id_fk;
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

	public String getBarcode_validity() {
		return barcode_validity;
	}

	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
	}

	public int getApply_goods_count() {
		return apply_goods_count;
	}

	public void setApply_goods_count(int apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}

	@Override
	public String toString() {
		return "LookInStoreApplyFormBean [goods_id=" + goods_id
				+ ", goods_name=" + goods_name + ", goods_brand=" + goods_brand
				+ ", goods_unit=" + goods_unit + ", goods_standard="
				+ goods_standard + ", goods_cas=" + goods_cas + ", type_name="
				+ type_name + ", barcode_validity=" + barcode_validity
				+ ", apply_goods_count=" + apply_goods_count + ", apply_id_fk="
				+ apply_id_fk + "]";
	}


}

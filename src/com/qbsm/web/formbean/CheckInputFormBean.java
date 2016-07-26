package com.qbsm.web.formbean;
/**
 * 验收入库视图
 * 单据管理详细表
 */
//涉及的物理表
public class CheckInputFormBean {
	
//关联t_goods
	private Integer goods_id; // 物资编号
	private String goods_name; // 物资名称
	private String goods_brand; // 物资品牌
	private String goods_unit; // 单位
	private String goods_standard; // 规格
	private String goods_cas; // 货号
	
//关联t_type
	private String goodstype_name; // 物资类别
	
//关联t_user表
	private String user_name;	//供应商

//关联t_barcode表
	private String barcode_validity; // 有效期
	private double barcodePrice;	//单价

//关联Inventory表
	private int inventory_residue;			//验收入库时的数量
	
	
	public double getBarcodePrice() {
		return barcodePrice;
	}
	public void setBarcodePrice(double barcodePrice) {
		this.barcodePrice = barcodePrice;
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
	public String getGoodstype_name() {
		return goodstype_name;
	}
	public void setGoodstype_name(String goodstype_name) {
		this.goodstype_name = goodstype_name;
	}
	public String getGoods_cas() {
		return goods_cas;
	}
	public void setGoods_cas(String goods_cas) {
		this.goods_cas = goods_cas;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getBarcode_validity() {
		return barcode_validity;
	}
	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
	}
	public int getInventory_residue() {
		return inventory_residue;
	}
	public void setInventory_residue(int inventory_residue) {
		this.inventory_residue = inventory_residue;
	}
	@Override
	public String toString() {
		return "CheckInputFormBean [goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand + ", goods_unit="
				+ goods_unit + ", goods_standard=" + goods_standard
				+ ", goods_cas=" + goods_cas + ", goodstype_name="
				+ goodstype_name + ", user_name=" + user_name
				+ ", barcode_validity=" + barcode_validity + ", barcodePrice="
				+ barcodePrice + ", inventory_residue=" + inventory_residue
				+ "]";
	}
	
}

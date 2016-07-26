package com.qbsm.web.formbean;

/**
 * 条形码视图
 */
//涉及物资信息表、
public class BarcodeFormBean {
	
//关联Goods表
	private Integer goods_id;			//物资编号
	private String goods_name;			//物资名称
	private String goods_brand;			//物资品牌
	private String goods_unit;			//单位
	private String goods_cas;			//货号
	
//关联type表
	private String goodstype_name;		//物资类别
	
//关联Inventory
	private int inventory_count;		//数量：供应商提供物资的数量
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
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
	public int getInventory_count() {
		return inventory_count;
	}
	public void setInventory_count(int inventory_count) {
		this.inventory_count = inventory_count;
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
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
}

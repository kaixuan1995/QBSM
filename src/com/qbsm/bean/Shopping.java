package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_shopping")
public class Shopping {

	@PrimaryColumn(value="shopping_id")
	private Integer shopping_id;				//购物车id
	
	@ForeignColumn(ColumnName="goods_id",Table="t_goods")
	private Integer goods_id_fk;				//物资编号
	private String goods_name;					//物资名称
	private String goods_brand;					//物资单位
	private String goods_standard;				//物资规格
	private String goods_cas;					//物资货号
	
//关联类别表
	private String type_name;					//物资类别
	private Integer apply_goods_count;			//申请数量
	private String apply_urgent;				//是否加急
		
	@ForeignColumn(ColumnName="user_id",Table="t_user")
	private Integer user_id_fk;					//用户id
	private String user_name;					//用户名
	
	//涉及到t_barcode
	private String barcode_validity;			//有效期
	
	//设计t_storehouse
	private String storehouse_name;				//仓库名称
	@ForeignColumn(ColumnName="storehouse_id",Table="t_storehouse")
	private Integer storehouse_id_fk;				//仓库id
	
	
	private Integer inventory_id_fk;
	
	public Integer getInventory_id_fk() {
		return inventory_id_fk;
	}
	public void setInventory_id_fk(Integer inventory_id_fk) {
		this.inventory_id_fk = inventory_id_fk;
	}
	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}
	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}
	public Integer getShopping_id() {
		return shopping_id;
	}
	public void setShopping_id(Integer shopping_id) {
		this.shopping_id = shopping_id;
	}
	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}
	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
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
	public Integer getApply_goods_count() {
		return apply_goods_count;
	}
	public void setApply_goods_count(Integer apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}
	

	public String getApply_urgent() {
		return apply_urgent;
	}
	public void setApply_urgent(String apply_urgent) {
		this.apply_urgent = apply_urgent;
	}
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
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
	public String getStorehouse_name() {
		return storehouse_name;
	}
	public void setStorehouse_name(String storehouse_name) {
		this.storehouse_name = storehouse_name;
	}
	@Override
	public String toString() {
		return "Shopping [shopping_id=" + shopping_id + ", goods_id_fk="
				+ goods_id_fk + ", goods_name=" + goods_name + ", goods_brand="
				+ goods_brand + ", goods_standard=" + goods_standard
				+ ", goods_cas=" + goods_cas + ", type_name=" + type_name
				+ ", apply_goods_count=" + apply_goods_count
				+ ", apply_urgent=" + apply_urgent + ", user_id_fk="
				+ user_id_fk + ", user_name=" + user_name
				+ ", barcode_validity=" + barcode_validity
				+ ", storehouse_name=" + storehouse_name
				+ ", storehouse_id_fk=" + storehouse_id_fk
				+ ", inventory_id_fk=" + inventory_id_fk + "]";
	}
}

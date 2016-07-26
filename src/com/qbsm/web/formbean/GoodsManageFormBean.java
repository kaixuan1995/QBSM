package com.qbsm.web.formbean;

/**
 * 物资信息管理视图
 */
//涉及物资信息表、库存表
public class GoodsManageFormBean {

//关联Goods表
	private Integer goods_id; // 物资编号
	private String goods_name; // 物资名称
	private String goods_brand; // 物资品牌
	private String goods_unit; // 单位
	private String goods_standard; // 规格
	private String goods_cas; // 货号
	private Integer goods_min; // 最低库存
	private Integer goods_max; // 最高库存
	
//关联t_type表
	private String type_name; // 物资类别
	
//关联Inventory
	private Integer inventory_id;		//库存id
	private String inventory_time;		//入库时间
	private Integer inventory_count;    // 数量：此处是入库物资的数量
	private Integer inventory_residue;  //现有库存
	
//关联user表
	private Integer user_id_fk;

	private String user_lianxiren; // 供应商联系人
	private String user_phone;// 供应商联系电话
	private String barcode_validity; // 有效期
	
	
	public Integer getInventory_residue() {
		return inventory_residue;
	}

	public void setInventory_residue(Integer inventory_residue) {
		this.inventory_residue = inventory_residue;
	}

	public String getInventory_time() {
		return inventory_time;
	}

	public void setInventory_time(String inventory_time) {
		this.inventory_time = inventory_time;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	
	public Integer getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
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



	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getGoods_cas() {
		return goods_cas;
	}

	public void setGoods_cas(String goods_cas) {
		this.goods_cas = goods_cas;
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

	public String getBarcode_validity() {
		return barcode_validity;
	}

	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
	}

	public Integer getInventory_count() {
		return inventory_count;
	}

	public void setInventory_count(Integer inventory_count) {
		this.inventory_count = inventory_count;
	}

	
	public Integer getGoods_min() {
		return goods_min;
	}

	public void setGoods_min(Integer goods_min) {
		this.goods_min = goods_min;
	}

	public Integer getGoods_max() {
		return goods_max;
	}

	public void setGoods_max(Integer goods_max) {
		this.goods_max = goods_max;
	}

	@Override
	public String toString() {
		return "GoodsManageFormBean [goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand + ", goods_unit="
				+ goods_unit + ", goods_standard=" + goods_standard
				+ ", goods_cas=" + goods_cas + ", goods_min=" + goods_min
				+ ", goods_max=" + goods_max + ", type_name=" + type_name
				+ ", inventory_id=" + inventory_id + ", inventory_time="
				+ inventory_time + ", inventory_count=" + inventory_count
				+ ", user_id_fk=" + user_id_fk + ", user_lianxiren="
				+ user_lianxiren + ", user_phone=" + user_phone
				+ ", barcode_validity=" + barcode_validity + "]";
	}



}

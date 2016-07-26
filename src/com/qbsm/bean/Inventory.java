package com.qbsm.bean;


import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//库存表  t_inventory
@Table("t_inventory")
public class Inventory {

	@PrimaryColumn("inventory_id")
	private Integer inventory_id; // 表id

	@ForeignColumn(ColumnName="user_id",Table="t_user")
	private Integer user_id_fk; // 入库人
	
	@ForeignColumn(ColumnName="storehouse_id",Table="t_storehouse")
	private Integer storehouse_id_fk; // 仓库
	
	@ForeignColumn(ColumnName="place_id", Table = "t_place")
	private Integer place_id_fk; // 仓库内分区点
	
	@ForeignColumn(ColumnName="goods_id", Table = "t_goods")
	private Integer goods_id_fk; // 物资
	
	@ForeignColumn(ColumnName="barcode_id", Table = "t_barcode")
	private Integer barcode_id_fk; // 条形码
	
	
	private Integer inventory_count; // 入库数量
	private String inventory_time; // 入库时间
	private Integer inventory_isdel; //是否逻辑删除

	public Integer getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}

	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}

	public Integer getPlace_id_fk() {
		return place_id_fk;
	}

	public void setPlace_id_fk(Integer place_id_fk) {
		this.place_id_fk = place_id_fk;
	}

	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}

	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}

	public Integer getBarcode_id_fk() {
		return barcode_id_fk;
	}

	public void setBarcode_id_fk(Integer barcode_id_fk) {
		this.barcode_id_fk = barcode_id_fk;
	}

	public Integer getInventory_count() {
		return inventory_count;
	}

	public void setInventory_count(Integer inventory_count) {
		this.inventory_count = inventory_count;
	}


	public String getInventory_time() {
		return inventory_time;
	}

	public void setInventory_time(String inventory_time) {
		this.inventory_time = inventory_time;
	}

	public Integer getInventory_isdel() {
		return inventory_isdel;
	}

	public void setInventory_isdel(Integer inventory_isdel) {
		this.inventory_isdel = inventory_isdel;
	}

}
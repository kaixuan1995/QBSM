package com.qbsm.bean;


import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//条形码 t_barcode

@Table("t_barcode")
public class Barcode {

	// Fields
	@PrimaryColumn("barcode_id")
	private Integer barcode_id; // 表id

	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer user_id_fk; // 供应商id

	@ForeignColumn(ColumnName = "goods_id", Table = "t_goods")
	private Integer goods_id_fk; // 物资id

	private String barcode_validity; // 有效期
	private Double barcode_price; // 单价
	private Integer barcodeIsdel; // 是否删除标记

	public Integer getBarcode_id() {
		return barcode_id;
	}

	public void setBarcode_id(Integer barcode_id) {
		this.barcode_id = barcode_id;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}

	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}

	public String getBarcode_validity() {
		return barcode_validity;
	}

	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
	}

	public Integer getBarcodeIsdel() {
		return barcodeIsdel;
	}

	public void setBarcodeIsdel(Integer barcodeIsdel) {
		this.barcodeIsdel = barcodeIsdel;

	}

	public Double getBarcode_price() {
		return barcode_price;
	}

	public void setBarcode_price(Double barcode_price) {
		this.barcode_price = barcode_price;
	}

 

}
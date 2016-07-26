package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_voucher_goods")
public class Voucher_Goods {

	// 凭证单于物资关系表
	@PrimaryColumn("voucher_goods_id")
	private Integer voucher_goods_id; // 表id

	@ForeignColumn(ColumnName = "voucher_id", Table = "t_voucher")
	private Integer voucher_id_fk; // 凭证单编号

	@ForeignColumn(ColumnName = "goods_id", Table = "t_goods")
	private Integer goods_id_fk; // 物资编号

	@ForeignColumn(ColumnName = "barcode_id", Table = "t_barcode")
	private Integer barcode_id_fk; // 条形码编号
	
	private Integer voucher_goods_count;// 数量
	private Integer voucher_goods_isdel; //

	public Integer getVoucher_goods_id() {
		return voucher_goods_id;
	}

	public void setVoucher_goods_id(Integer voucher_goods_id) {
		this.voucher_goods_id = voucher_goods_id;
	}

	public Integer getVoucher_id_fk() {
		return voucher_id_fk;
	}

	public void setVoucher_id_fk(Integer voucher_id_fk) {
		this.voucher_id_fk = voucher_id_fk;
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

	public Integer getVoucher_goods_count() {
		return voucher_goods_count;
	}

	public void setVoucher_goods_count(Integer voucher_goods_count) {
		this.voucher_goods_count = voucher_goods_count;
	}

	public Integer getVoucher_goods_isdel() {
		return voucher_goods_isdel;
	}

	public void setVoucher_goods_isdel(Integer voucher_goods_isdel) {
		this.voucher_goods_isdel = voucher_goods_isdel;
	}

}
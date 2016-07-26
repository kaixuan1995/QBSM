package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;


@Table(value="t_goods_number")
public class Goods_number {

	@PrimaryColumn(value="goods_number_id")
	private Integer goods_number_id;
	
	@ForeignColumn(ColumnName="goods_id",Table="t_goods")
	private Integer goods_id_fk;
	
	@ForeignColumn(ColumnName="storehouse_id",Table="t_storehouse")
	private Integer storehouse_id_fk;
	
	private Integer goods_number;

	public Integer getGoods_number_id() {
		return goods_number_id;
	}

	public void setGoods_number_id(Integer goods_number_id) {
		this.goods_number_id = goods_number_id;
	}

	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}

	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}

	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}

	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}

	public Integer getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(Integer goods_number) {
		this.goods_number = goods_number;
	}

	@Override
	public String toString() {
		return "Goods_number [goods_number_id=" + goods_number_id
				+ ", goods_id_fk=" + goods_id_fk + ", storehouse_id_fk="
				+ storehouse_id_fk + ", goods_number=" + goods_number + "]";
	}
	
	
}

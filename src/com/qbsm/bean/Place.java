package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//仓库分区
@Table("t_place")
public class Place {
	@PrimaryColumn("place_id")
	private Integer place_id; // biao id

	@ForeignColumn(ColumnName = "storehouse_id", Table = "t_storehouse")
	private Integer storehouse_id_fk; // 仓库编号

	private String place_name; // 分区名称
	private Integer place_isdel; //

	public Integer getPlace_id() {
		return place_id;
	}

	public void setPlace_id(Integer place_id) {
		this.place_id = place_id;
	}

	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}

	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public Integer getPlace_isdel() {
		return place_isdel;
	}

	public void setPlace_isdel(Integer place_isdel) {
		this.place_isdel = place_isdel;
	}

	@Override
	public String toString() {
		return "Place [place_id=" + place_id + ", storehouse_id_fk="
				+ storehouse_id_fk + ", place_name=" + place_name
				+ ", place_isdel=" + place_isdel + "]";
	}
	
}
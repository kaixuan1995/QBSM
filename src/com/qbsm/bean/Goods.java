package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//物资 t_goods
@Table("t_goods")
public class Goods {

	@PrimaryColumn("goods_id")
	
	private Integer goods_id; // 物资id

	private String goods_name; // 名称
	private String goods_brand; // 品牌
	private String goods_unit; // 单位
	private String goods_standard; // 规格
	private String goods_cas; // 货号
	
	@ForeignColumn(ColumnName = "type_id", Table = "t_type")
	private Integer type_id_fk; // 类型id
	
	
	private Integer goods_min; // 最小库存
	private Integer goods_max; // 最高库存
	private Integer goods_isdel; // 是否删除标记：0是没有被删除，1是已经被删除

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public Integer getType_id_fk() {
		return type_id_fk;
	}

	public void setType_id_fk(Integer type_id_fk) {
		this.type_id_fk = type_id_fk;
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

	public Integer getGoods_isdel() {
		return goods_isdel;
	}

	public void setGoods_isdel(Integer goods_isdel) {
		this.goods_isdel = goods_isdel;
	}

	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name
				+ ", goods_brand=" + goods_brand + ", goods_unit=" + goods_unit
				+ ", goods_standard=" + goods_standard + ", goods_cas="
				+ goods_cas + ", type_id_fk=" + type_id_fk + ", goods_min="
				+ goods_min + ", goods_max=" + goods_max + ", goods_isdel="
				+ goods_isdel + "]";
	}

}
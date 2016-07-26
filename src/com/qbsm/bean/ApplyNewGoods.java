package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;


//新增物资申请单表 t_applygoods
@Table("t_applynewgoods")
public class ApplyNewGoods {

	@PrimaryColumn("applygoods_id")
	private Integer applygoods_id;			//申请物资id
	
	private String apply_time; 			// 申请时间
	@ForeignColumn(ColumnName="user_id",Table="t_user")
	private Integer user_id_fk;   		//申请id
	private String user_lianxiren; 		// 申请人
	private String goods_name; 			// 申请的物资的名称
	private String goods_brand; 			// 申请的物资的品牌
	private String goods_unit; 			// 申请的物资的单位
	private String goods_standard; 		//申请物资的规格
	
	@ForeignColumn(ColumnName="type_id",Table="t_type")
	private Integer type_id_fk;		// 申请的物资的类型
	private String type_name; //物资类别

	private String goods_cas; 			// 申请的物资的货号
	private String apply_state; 			// 状态:待审核，审核通过    审核失败，采购中   已到货   已入库    已领用
	public Integer getApplygoods_id() {
		return applygoods_id;
	}
	public void setApplygoods_id(Integer applygoods_id) {
		this.applygoods_id = applygoods_id;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	public String getUser_lianxiren() {
		return user_lianxiren;
	}
	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
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
	public Integer getType_id_fk() {
		return type_id_fk;
	}
	public void setType_id_fk(Integer type_id_fk) {
		this.type_id_fk = type_id_fk;
	}
	public String getGoods_cas() {
		return goods_cas;
	}
	public void setGoods_cas(String goods_cas) {
		this.goods_cas = goods_cas;
	}
	public String getApply_state() {
		return apply_state;
	}
	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	@Override
	public String toString() {
		return "ApplyNewGoods [applygoods_id=" + applygoods_id
				+ ", apply_time=" + apply_time + ", user_id_fk=" + user_id_fk
				+ ", user_lianxiren=" + user_lianxiren + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand + ", goods_unit="
				+ goods_unit + ", goods_standard=" + goods_standard
				+ ", type_id_fk=" + type_id_fk + ", type_name=" + type_name
				+ ", goods_cas=" + goods_cas + ", apply_state=" + apply_state
				+ "]";
	}
	
}
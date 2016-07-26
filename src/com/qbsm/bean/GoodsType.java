package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;


//物资类型表
@Table("t_goodstype")
public class GoodsType{
	 @PrimaryColumn("type_id")
     private Integer type_id;	//类型id
     private String type_name;	//类型名称
     private String type_description;	//类型描述
     private Integer type_isdel;		//是否逻辑删除
     
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getType_description() {
		return type_description;
	}
	public void setType_description(String type_description) {
		this.type_description = type_description;
	}
	public Integer getType_isdel() {
		return type_isdel;
	}
	public void setType_isdel(Integer type_isdel) {
		this.type_isdel = type_isdel;
	}
	@Override
	public String toString() {
		return "GoodsType [type_id=" + type_id + ", type_name=" + type_name
				+ ", type_description=" + type_description + ", type_isdel="
				+ type_isdel + "]";
	}
    
}
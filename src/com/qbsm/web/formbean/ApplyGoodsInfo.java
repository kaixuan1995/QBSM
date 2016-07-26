package com.qbsm.web.formbean;

//人员请购以及领用的物资信息视图
public class ApplyGoodsInfo {
	private int user_id;     //检测室人员id
	private String goods_name;		//物资名称
	private String goods_brand;		//物资品牌
	private String goods_unit;			//物资单位
	private String goods_standard;		//物资规格
	private String goods_cas;		//物资货号
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;	
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
	@Override
	public String toString() {
		return "ApplyGoodsInfo [user_id=" + user_id + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand + ", goods_unit="
				+ goods_unit + ", goods_standard=" + goods_standard
				+ ", goods_cas=" + goods_cas + "]";
	}
	
	
	
}

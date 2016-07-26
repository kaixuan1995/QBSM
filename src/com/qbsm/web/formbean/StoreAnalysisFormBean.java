package com.qbsm.web.formbean;
	
//仓库进出仓以及采购统计视图
public class StoreAnalysisFormBean {
	private Integer user_id;
	private String user_name;		//经手人
	private String goods_name;		//物资名称
	private String goods_brand;		//物资品牌
	private String goods_cas;		//物资货号
	private String goods_standard;		//物资规格
	private String goods_unit;			//物资单位
	private Integer voucher_goods_count;		//出库或者入库数量
	private String voucher_createtime;		//出库或者入库时间
    private Integer type_id_fk;            //物资类别
    private Integer Storehouse_id;
	
	public Integer getStorehouse_id() {
		return Storehouse_id;
	}

	public void setStorehouse_id(Integer storehouse_id) {
		Storehouse_id = storehouse_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getType_id_fk() {
		return type_id_fk;
	}

	public void setType_id_fk(Integer type_id_fk) {
		this.type_id_fk = type_id_fk;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getVoucher_createtime() {
		return voucher_createtime;
	}

	public void setVoucher_createtime(String voucher_createtime) {
		this.voucher_createtime = voucher_createtime;
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

	public String getGoods_cas() {
		return goods_cas;
	}

	public void setGoods_cas(String goods_cas) {
		this.goods_cas = goods_cas;
	}

	public String getGoods_standard() {
		return goods_standard;
	}

	public void setGoods_standard(String goods_standard) {
		this.goods_standard = goods_standard;
	}

	public String getGoods_unit() {
		return goods_unit;
	}

	public void setGoods_unit(String goods_unit) {
		this.goods_unit = goods_unit;
	}

	public Integer getVoucher_goods_count() {
		return voucher_goods_count;
	}

	public void setVoucher_goods_count(Integer voucher_goods_count) {
		this.voucher_goods_count = voucher_goods_count;
	}

	@Override
	public String toString() {
		return "StoreAnalysisFormBean [user_id=" + user_id + ", user_name="
				+ user_name + ", goods_name=" + goods_name + ", goods_brand="
				+ goods_brand + ", goods_cas=" + goods_cas
				+ ", goods_standard=" + goods_standard + ", goods_unit="
				+ goods_unit + ", voucher_goods_count=" + voucher_goods_count
				+ ", voucher_createtime=" + voucher_createtime
				+ ", goods_type_id=" + type_id_fk + ", Storehouse_id="
				+ Storehouse_id + "]";
	}


}

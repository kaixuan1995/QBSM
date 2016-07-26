package com.qbsm.web.formbean;

//供应商供货异常绩效
public class SupplierAnalysisFromBean {
	private int voucher_id; 		// 对应的异常单号
	private String goods_name;
	private String goods_brand;
	private String goods_cas;
	private String goods_standard;
	private String goods_unit;
	private Integer voucher_goods_count;   //退货数量
	private String voucher_remark;
	private String apply_picture_url;  //物资退货单中提供的图片数据
	private int user_id;  //供应商id
	
	public int getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
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
	public String getVoucher_remark() {
		return voucher_remark;
	}
	public void setVoucher_remark(String voucher_remark) {
		this.voucher_remark = voucher_remark;
	}
	public String getApply_picture_url() {
		return apply_picture_url;
	}
	public void setApply_picture_url(String apply_picture_url) {
		this.apply_picture_url = apply_picture_url;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "SupplierAnalysisFromBean [voucher_id=" + voucher_id
				+ ", goods_name=" + goods_name + ", goods_cas=" + goods_cas
				+ ", goods_brand=" + goods_brand + ", goods_cas=" + goods_cas
				+ ", goods_standard=" + goods_standard + ", goods_unit="
				+ goods_unit + ", voucher_goods_count=" + voucher_goods_count
				+ ", voucher_remark=" + voucher_remark + ", apply_picture_url="
				+ apply_picture_url + ", user_id=" + user_id + "]";
	}

}

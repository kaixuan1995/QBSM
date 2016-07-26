package com.qbsm.web.formbean;

/**
 * 单据信息视图
 */
public class VoucherDetailFormBean {
	
//关联t_goods
	private String  goods_name;			//物资名称
	private String  goods_brand;		//物资品牌
	
	private String  goods_unit;			//单位
	private String  goods_standard;		//规格
	private String  goods_cas;			//货号
//关联t_user
	private String user_lianxiren;		//申请人
	private Integer voucher_type;// 凭证类型   
	private String voucher_createtime;// 凭证创建时间
	private String voucher_remark; // 备注
	private Integer voucher_goods_count;//申请数量
//关联二维码表
    private String barcode_validity; // 有效期
//Goods_number
    private Integer goods_number;
//t_voucher
    private Integer voucher_id;
  
    
	@Override
	public String toString() {
		return "VoucherDetailFormBean [goods_name=" + goods_name
				+ ", goods_brand=" + goods_brand + ", goods_unit=" + goods_unit
				+ ", goods_standard=" + goods_standard + ", goods_cas="
				+ goods_cas + ", user_lianxiren=" + user_lianxiren
				+ ", voucher_type=" + voucher_type + ", voucher_createtime="
				+ voucher_createtime + ", voucher_remark=" + voucher_remark
				+ ", voucher_goods_count=" + voucher_goods_count
				+ ", barcode_validity=" + barcode_validity + ", goods_number="
				+ goods_number + ", voucher_id=" + voucher_id + "]";
	}
	public Integer getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(Integer voucher_id) {
		this.voucher_id = voucher_id;
	}
	public Integer getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(Integer goods_number) {
		this.goods_number = goods_number;
	}
	public String getVoucher_createtime() {
		return voucher_createtime;
	}
	public void setVoucher_createtime(String voucher_createtime) {
		this.voucher_createtime = voucher_createtime;
	}
	public String getBarcode_validity() {
		return barcode_validity;
	}
	public void setBarcode_validity(String barcode_validity) {
		this.barcode_validity = barcode_validity;
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
	public String getUser_lianxiren() {
		return user_lianxiren;
	}
	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}
	public Integer getVoucher_type() {
		return voucher_type;
	}
	public void setVoucher_type(Integer voucher_type) {
		this.voucher_type = voucher_type;
	}
	public String getVoucher_remark() {
		return voucher_remark;
	}
	public void setVoucher_remark(String voucher_remark) {
		this.voucher_remark = voucher_remark;
	}
	public Integer getVoucher_goods_count() {
		return voucher_goods_count;
	}
	public void setVoucher_goods_count(Integer voucher_goods_count) {
		this.voucher_goods_count = voucher_goods_count;
	}
	
	
}

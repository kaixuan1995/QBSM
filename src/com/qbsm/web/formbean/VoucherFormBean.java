package com.qbsm.web.formbean;


/**
 * 单据管理视图
 */
public class VoucherFormBean {
	// 凭证表：t_voucher
	// 供应商表：t_storehouse
	// 用户表：t_user
	private Integer voucher_id; // 凭证单编号
	private Integer user_id_fk; // 经手人编号
	private String user_name; // 经手人
	private Integer storehouse_id_fk; // 涉及仓库编号
	private String storehouse_name; // 涉及仓库
	private String voucher_createtime; // 单据生成时间
	private Integer voucher_brokerage; // 供应商id
	private String user_lianxiren; // 供应商--对应物资的供应商姓名
	private String voucher_remark; // 备注
	private Integer voucher_isdel; // 是否逻辑删除
	private Integer voucher_type;// 凭证类型   
	//1采购单凭证，2个人采购凭证，3入库凭证，4出库凭证，5领用/退用凭证，6退货凭证，7仓库调用凭证

	public Integer getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(Integer voucher_id) {
		this.voucher_id = voucher_id;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}

	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}

	public String getVoucher_createtime() {
		return voucher_createtime;
	}

	public void setVoucher_createtime(String voucher_createtime) {
		this.voucher_createtime = voucher_createtime;
	}

	public Integer getVoucher_brokerage() {
		return voucher_brokerage;
	}

	public void setVoucher_brokerage(Integer voucher_brokerage) {
		this.voucher_brokerage = voucher_brokerage;
	}

	public String getVoucher_remark() {
		return voucher_remark;
	}

	public void setVoucher_remark(String voucher_remark) {
		this.voucher_remark = voucher_remark;
	}

	public Integer getVoucher_isdel() {
		return voucher_isdel;
	}

	public void setVoucher_isdel(Integer voucher_isdel) {
		this.voucher_isdel = voucher_isdel;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStorehouse_name() {
		return storehouse_name;
	}

	public void setStorehouse_name(String storehouse_name) {
		this.storehouse_name = storehouse_name;
	}

	@Override
	public String toString() {
		return "VoucherFormBean [voucher_id=" + voucher_id + ", user_id_fk=" + user_id_fk + ", user_name=" + user_name + ", storehouse_id_fk="
				+ storehouse_id_fk + ", storehouse_name=" + storehouse_name + ", voucher_createtime=" + voucher_createtime + ", voucher_brokerage="
				+ voucher_brokerage + ", user_lianxiren=" + user_lianxiren + ", voucher_remark=" + voucher_remark + ", voucher_isdel="
				+ voucher_isdel + ", voucher_type=" + voucher_type + "]";
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

}

package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//凭证单
@Table("t_voucher")
public class Voucher {
	@PrimaryColumn("voucher_id")
	private Integer voucher_id; // 凭证单编号

	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer user_id_fk; // 经手人编号
	
	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer applyuser_id_fk; // 申请人编号


	@Override
	public String toString() {
		return "Voucher [voucher_id=" + voucher_id + ", user_id_fk="
				+ user_id_fk + ", applyuser_id_fk=" + applyuser_id_fk
				+ ", storehouse_id_fk=" + storehouse_id_fk
				+ ", voucher_createtime=" + voucher_createtime
				+ ", voucher_brokerage=" + voucher_brokerage
				+ ", voucher_remark=" + voucher_remark + ", voucher_isdel="
				+ voucher_isdel + ", voucher_type=" + voucher_type + "]";
	}

	public Integer getApplyuser_id_fk() {
		return applyuser_id_fk;
	}

	public void setApplyuser_id_fk(Integer applyuser_id_fk) {
		this.applyuser_id_fk = applyuser_id_fk;
	}

	@ForeignColumn(ColumnName = "storehouse_id", Table = "t_storehouse")
	private Integer storehouse_id_fk; // 涉及仓库编号

	private String voucher_createtime; // 单据生成时间
	private Integer voucher_brokerage; // 供应商id
	private String voucher_remark; // 备注
	private Integer voucher_isdel; // 是否逻辑删除
	private Integer voucher_type;
	// 凭证类型 1采购单凭证单，2个人采购凭证单，3入库凭证单，
	//4出库凭证单，5领用凭证单，6退货凭证单，7仓库调用凭证单,9退用凭证单
	// 1采购单凭证，2个人采购凭证，不需要仓库id

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

	public Integer getVoucher_type() {
		return voucher_type;
	}

	public void setVoucher_type(Integer voucher_type) {
		this.voucher_type = voucher_type;
	}

}
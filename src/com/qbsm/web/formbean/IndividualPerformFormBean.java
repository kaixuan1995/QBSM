package com.qbsm.web.formbean;

/**
 * 个人 绩效视图(与详个人绩效详细视图放在一起)
 */
public class IndividualPerformFormBean {
	private Integer user_id; // 员工id
	private String user_name; // 员工姓名
	private Integer goods_id; // 物资id
	private String goods_name; // 物资名称
	private String goods_brand; // 物资品牌
	private String goods_unitl; // 单位
	private String goods_standard; // 规格
	private Integer apply_goods_count; // 申请数量：由计算得到
	private Integer voucher_goods_count; // 领用数量：由计算得到
	private String result; // 评定结果
	private Integer infactOutput; // 实际生产
	private char isRight; // 是否符合

	public Integer getInfactOutput() {
		return infactOutput;
	}

	public void setInfactOutput(Integer infactOutput) {
		this.infactOutput = infactOutput;
	}

	public char getIsRight() {
		return isRight;
	}

	public void setIsRight(char isRight) {
		this.isRight = isRight;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
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

	public String getGoods_unitl() {
		return goods_unitl;
	}

	public void setGoods_unitl(String goods_unitl) {
		this.goods_unitl = goods_unitl;
	}

	public String getGoods_standard() {
		return goods_standard;
	}

	public void setGoods_standard(String goods_standard) {
		this.goods_standard = goods_standard;
	}

	public Integer getApply_goods_count() {
		return apply_goods_count;
	}

	public void setApply_goods_count(Integer apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}

	public Integer getVoucher_goods_count() {
		return voucher_goods_count;
	}

	public void setVoucher_goods_count(Integer voucher_goods_count) {
		this.voucher_goods_count = voucher_goods_count;
	}

	@Override
	public String toString() {
		return "IndividualPerformFormBean [user_id=" + user_id + ", user_name="
				+ user_name + ", goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_brand=" + goods_brand
				+ ", goods_unitl=" + goods_unitl + ", goods_standard="
				+ goods_standard + ", apply_goods_count=" + apply_goods_count
				+ ", voucher_goods_count=" + voucher_goods_count + ", result="
				+ result + ", infactOutput=" + infactOutput + ", isRight="
				+ isRight + "]";
	}

}

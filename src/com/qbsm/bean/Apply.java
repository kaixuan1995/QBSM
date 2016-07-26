package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//对应t_apply （申请单表）

@Table("t_apply")
public class Apply{

	// Fields
	@PrimaryColumn("apply_id")
	private Integer apply_id; // 申请单编号

	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer user_id_fk; // 申请人编号

	@ForeignColumn(ColumnName = "storehouse_id", Table = "t_storehouse")
	private Integer storehouse_id_fk; // 涉及仓库编号

	private String apply_time; 		// 申请时间
	private String apply_urgent; 	// 是否加急
	private String apply_state; 	// 状态:待审核，审核通过    审核失败，采购中   已到货    已入库    已领用 已验收
	private String apply_type; 		// 单据类型:1申请入库  2申请采购 3申请领用 4申请退用 5申请退货  6仓库之间的调度 
	private String apply_remark; 	// 备注
	private Integer apply_isdel; 	// 逻辑删除标记为
	
	
	
	public Integer getApply_id() {
		return apply_id;
	}
	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
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
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	public String getApply_urgent() {
		return apply_urgent;
	}
	public void setApply_urgent(String apply_urgent) {
		this.apply_urgent = apply_urgent;
	}
	public String getApply_type() {
		return apply_type;
	}
	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}
	public String getApply_remark() {
		return apply_remark;
	}
	public void setApply_remark(String apply_remark) {
		this.apply_remark = apply_remark;
	}
	public Integer getApply_isdel() {
		return apply_isdel;
	}
	public void setApply_isdel(Integer apply_isdel) {
		this.apply_isdel = apply_isdel;
	}
	
	public String getApply_state() {
		return apply_state;
	}
	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}
	@Override
	public String toString() {
		return "Apply [apply_id=" + apply_id + ", user_id_fk=" + user_id_fk
				+ ", storehouse_id_fk=" + storehouse_id_fk + ", apply_time="
				+ apply_time + ", apply_urgent=" + apply_urgent
				+ ", apply_state=" + apply_state + ", apply_type=" + apply_type
				+ ", apply_remark=" + apply_remark + ", apply_isdel="
				+ apply_isdel + "]";
	}
	
	

}
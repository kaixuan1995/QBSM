package com.qbsm.bean;


import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_office")
public class Office {

	@PrimaryColumn("office_id")
	private Integer office_id; // 表id

	@ForeignColumn(ColumnName = "storehouse_id", Table = "t_storehouse")
	private Integer storehouse_id_fk; // 仓库id
	
	
	private String office_name; // 科室名称
	private String office_createtime; // 科室创建时间
	private Integer office_isdel; //是否物理删除

	public Integer getOffice_id() {
		return office_id;
	}

	public void setOffice_id(Integer office_id) {
		this.office_id = office_id;
	}

	public Integer getStorehouse_id_fk() {
		return storehouse_id_fk;
	}

	public void setStorehouse_id_fk(Integer storehouse_id_fk) {
		this.storehouse_id_fk = storehouse_id_fk;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public String getOffice_createtime() {
		return office_createtime;
	}

	public void setOffice_createtime(String office_createtime) {
		this.office_createtime = office_createtime;
	}

	public Integer getOffice_isdel() {
		return office_isdel;
	}

	public void setOffice_isdel(Integer office_isdel) {
		this.office_isdel = office_isdel;
	}

	@Override
	public String toString() {
		return "Office [office_id=" + office_id + ", storehouse_id_fk="
				+ storehouse_id_fk + ", office_name=" + office_name
				+ ", office_createtime=" + office_createtime
				+ ", office_isdel=" + office_isdel + "]";
	}

}
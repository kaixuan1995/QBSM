package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//供应商与物资关系表
@Table("t_supplier_goodstype")
public class Supplier_Goodstype {

	@PrimaryColumn("supplier_goodstype_id")
	private Integer supplier_goodstype_id; // 表id

	@ForeignColumn(ColumnName = "user_id", Table = "t_user")
	private Integer user_id_fk; // 供应商id

	@ForeignColumn(ColumnName = "type_id", Table = "t_type")
	private Integer type_id_fk; // 类别

	private Integer supplier_goodstype_isdel;    //是否逻辑删除

	public Integer getSupplier_goodstype_id() {
		return supplier_goodstype_id;
	}

	public void setSupplier_goodstype_id(Integer supplier_goodstype_id) {
		this.supplier_goodstype_id = supplier_goodstype_id;
	}

	public Integer getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Integer getType_id_fk() {
		return type_id_fk;
	}

	public void setType_id_fk(Integer type_id_fk) {
		this.type_id_fk = type_id_fk;
	}

	public Integer getSupplier_goodstype_isdel() {
		return supplier_goodstype_isdel;
	}

	public void setSupplier_goodstype_isdel(Integer supplier_goodstype_isdel) {
		this.supplier_goodstype_isdel = supplier_goodstype_isdel;
	}

}
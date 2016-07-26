package com.qbsm.bean;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//申请单详细表 t_apply_goods

@Table("t_apply_goods")
public class Apply_Goods {
	@PrimaryColumn("apply_goods_id")
	private Integer apply_goods_id;			//表id
	
	@ForeignColumn(ColumnName="apply_id",Table="t_apply")
	private Integer apply_id_fk;			//申请单号
	
	@ForeignColumn(ColumnName="goods_id",Table="t_goods")
	private Integer goods_id_fk;			//物资id
	 
	
	private Integer apply_goods_count;		//物资数量
	private Integer apply_goods_isdel;		//是否删除标记
	
	
	public Integer getApply_goods_id() {
		return apply_goods_id;
	}
	public void setApply_goods_id(Integer apply_goods_id) {
		this.apply_goods_id = apply_goods_id;
	}
	
	public Integer getApply_id_fk() {
		return apply_id_fk;
	}
	public void setApply_id_fk(Integer apply_id_fk) {
		this.apply_id_fk = apply_id_fk;
	}
	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}
	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}
	public Integer getApply_goods_count() {
		return apply_goods_count;
	}
	public void setApply_goods_count(Integer apply_goods_count) {
		this.apply_goods_count = apply_goods_count;
	}
	public Integer getApply_goods_isdel() {
		return apply_goods_isdel;
	}
	public void setApply_goods_isdel(Integer apply_goods_isdel) {
		this.apply_goods_isdel = apply_goods_isdel;
	}
	
	
}

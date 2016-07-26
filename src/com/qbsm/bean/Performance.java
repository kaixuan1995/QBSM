package com.qbsm.bean;



import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//个人绩效表
@Table("t_performance")
public class Performance{

	@PrimaryColumn("performance_id")
     private Integer performance_id;	
	
	@ForeignColumn(ColumnName="user_id",Table="t_user")
     private Integer user_id_fk;				//用户
	
	@ForeignColumn(ColumnName="goods_id",Table="t_goods")
     private Integer goods_id_fk;				//物资
     
     private String performance_time;			//时间
     private Integer performance_result;		//结果 
     private Integer performance_isdel; 		//是否逻辑删除：0代表没有逻辑删除   1代表逻辑删除
	public Integer getPerformance_id() {
		return performance_id;
	}
	public void setPerformance_id(Integer performance_id) {
		this.performance_id = performance_id;
	}
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public Integer getGoods_id_fk() {
		return goods_id_fk;
	}
	public void setGoods_id_fk(Integer goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}
	public String getPerformance_time() {
		return performance_time;
	}
	public void setPerformance_time(String performance_time) {
		this.performance_time = performance_time;
	}
	public Integer getPerformance_result() {
		return performance_result;
	}
	public void setPerformance_result(Integer performance_result) {
		this.performance_result = performance_result;
	}
	public Integer getPerformance_isdel() {
		return performance_isdel;
	}
	public void setPerformance_isdel(Integer performance_isdel) {
		this.performance_isdel = performance_isdel;
	}


  
}
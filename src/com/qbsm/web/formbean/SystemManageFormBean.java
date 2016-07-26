package com.qbsm.web.formbean;


/**
 * 系统维护视图
 */
public class SystemManageFormBean {
	private Integer copy_id;				//备份编号
	private String copy_name;		//备份名称
	private String copy_time;		//备份时间
	public Integer getCopy_id() {
		return copy_id;
	}
	public void setCopy_id(Integer copy_id) {
		this.copy_id = copy_id;
	}
	public String getCopy_name() {
		return copy_name;
	}
	public void setCopy_name(String copy_name) {
		this.copy_name = copy_name;
	}
	public String getCopy_time() {
		return copy_time;
	}
	public void setCopy_time(String copy_time) {
		this.copy_time = copy_time;
	}
	
}

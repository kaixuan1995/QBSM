package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//申请单于图片关系表 t_apply_picture
@Table("t_apply_picture")
public class ApplyPicture {

	// Fields
	@PrimaryColumn("apply_picture_id")
	private Integer apply_picture_id; 			// 表id
	private Integer apply_id; 					// 申请单id
	private String apply_picture_url; 			// 图片路径

	public Integer getApply_picture_id() {
		return apply_picture_id;
	}

	public void setApply_picture_id(Integer apply_picture_id) {
		this.apply_picture_id = apply_picture_id;
	}

	public Integer getApply_id() {
		return apply_id;
	}

	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}

	public String getApply_picture_url() {
		return apply_picture_url;
	}

	public void setApply_picture_url(String apply_picture_url) {
		this.apply_picture_url = apply_picture_url;
	}

	// Constructors

}
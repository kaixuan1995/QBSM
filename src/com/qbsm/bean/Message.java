package com.qbsm.bean;


import java.io.Serializable;

import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

//消息表 t_message
@Table("t_message")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryColumn("message_id")
	private Integer message_id; // 表id
	
	@ForeignColumn(ColumnName="apply_id", Table = "t_apply")
	private Integer apply_id_fk; // 申请单号
	
	@ForeignColumn(ColumnName="user_id", Table = "t_user")
	private Integer user_id_fk; // 用户id
	
	
	private String message_content; // 消息类型
	private String message_state; // 是否被查看状态
	private String message_time; // 时间
	private Integer message_isdel; //是否逻辑删除：0代表没有逻辑删除   1代表逻辑删除
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getApply_id_fk() {
		return apply_id_fk;
	}
	public void setApply_id_fk(Integer apply_id_fk) {
		this.apply_id_fk = apply_id_fk;
	}
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	public String getMessage_state() {
		return message_state;
	}
	public void setMessage_state(String message_state) {
		this.message_state = message_state;
	}
	public String getMessage_time() {
		return message_time;
	}
	public Integer getMessage_isdel() {
		return message_isdel;
	}
	public void setMessage_isdel(Integer message_isdel) {
		this.message_isdel = message_isdel;
	}
	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", apply_id_fk="
				+ apply_id_fk + ", user_id_fk=" + user_id_fk
				+ ", message_content=" + message_content + ", message_state="
				+ message_state + ", message_time=" + message_time
				+ ", message_isdel=" + message_isdel + "]";
	}

	

}
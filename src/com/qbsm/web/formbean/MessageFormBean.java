package com.qbsm.web.formbean;

/**
 * 首页消息视图
 */
public class MessageFormBean {
	private int message_id;
	// private String user_name; //用户名
	// private String message; //消息：动态消息
	private String message_time; // 消息时间
	private String message_content; // 消息内容
	private String apply_id_fk;
	private int user_id;  //用户id

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getApply_id_fk() {
		return apply_id_fk;
	}

	public void setApply_id_fk(String apply_id_fk) {
		this.apply_id_fk = apply_id_fk;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "MessageFormBean [message_id=" + message_id + ", message_time="
				+ message_time + ", message_content=" + message_content
				+ ", apply_id_fk=" + apply_id_fk + ", user_id=" + user_id + "]";
	}


}

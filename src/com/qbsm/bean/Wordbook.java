package com.qbsm.bean;

import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;

@Table("t_wordbook")
public class Wordbook {

	@PrimaryColumn("wordbook_id")
	private Integer wordbook_id;

	private Integer wordbook_number; // 编号
	private String wordbook_name; // 名称
	private String wordbook_type; //类型
	public String getWordbook_type() {
		return wordbook_type;
	}

	public void setWordbook_type(String wordbook_type) {
		this.wordbook_type = wordbook_type;
	}

	private Integer wordbook_isdel; //

	public Integer getWordbook_id() {
		return wordbook_id;
	}

	public void setWordbook_id(Integer wordbook_id) {
		this.wordbook_id = wordbook_id;
	}

	public Integer getWordbook_number() {
		return wordbook_number;
	}

	public void setWordbook_number(Integer wordbook_number) {
		this.wordbook_number = wordbook_number;
	}

	public String getWordbook_name() {
		return wordbook_name;
	}

	public void setWordbook_name(String wordbook_name) {
		this.wordbook_name = wordbook_name;
	}

	public Integer getWordbook_isdel() {
		return wordbook_isdel;
	}

	public void setWordbook_isdel(Integer wordbook_isdel) {
		this.wordbook_isdel = wordbook_isdel;
	}

	@Override
	public String toString() {
		return "Wordbook [wordbook_id=" + wordbook_id + ", wordbook_number="
				+ wordbook_number + ", wordbook_name=" + wordbook_name
				+ ", wordbook_type=" + wordbook_type + ", wordbook_isdel="
				+ wordbook_isdel + "]";
	}
	
	

}
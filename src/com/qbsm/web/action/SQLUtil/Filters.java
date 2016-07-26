package com.qbsm.web.action.SQLUtil;

import java.util.List;

public class Filters {
	private String groupOp;
	private List<Rules> rules;
	private String Sidx;
	private String Sord;
	
	
	public String getSidx() {
		return Sidx;
	}
	public void setSidx(String sidx) {
		Sidx = sidx;
	}
	public String getSord() {
		return Sord;
	}
	public void setSord(String sord) {
		Sord = sord;
	}
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<Rules> getRules() {
		return rules;
	}
	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}
	@Override
	public String toString() {
		return "Filters [groupOp=" + groupOp + ", rules=" + rules + ", Sidx="
				+ Sidx + ", Sord=" + Sord + "]";
	}
	
	/**
	 * 之前的sql:select count(wordbook_id) from t_wordbook
	 * 原sql中没有where,此方法添加sql如: {WHERE  id  >='10' AND  sex  ='女'} 
	 * @return select count(wordbook_id) from t_wordbook 
	 * 		   {WHERE  id  >='10' AND  sex  ='女'} 
	 */
	public String toWhereSql() {
		StringBuffer sql = new StringBuffer(" ");
		if (rules!=null) {
			sql.append("WHERE").append(" ");
			for(int i=0;i<rules.size();i++){
				sql.append(rules.get(i).toSql());
				if(i != rules.size()-1){
					sql.append(groupOp).append(" ");
				}
			}
		}
		if (!"".equals(Sidx)&&Sidx!=null) {
			sql.append(" ORDER BY ").append(Sidx).append(" ").append(Sord).append(" ");
		}
		return sql.toString();
	}
	/**
	 * 之前的sql:select count(*) from t_user where user_isdel = 0
	 * 原sql中有where, 此方法添加sql如: {AND  id  >='10' AND  sex  ='女'}
	 * @return select count(*) from t_user where user_isdel = 0 
	 * 			{AND  id  >='10' AND  sex  ='女'}
	 */
	public String toAndSql() {
		StringBuffer sql = new StringBuffer(" ");
		if (rules!=null) {
			sql.append(groupOp).append(" ");
			for(int i=0;i<rules.size();i++){
				sql.append(rules.get(i).toSql());
				if(i != rules.size()-1){
					sql.append(groupOp).append(" ");
				}
			}
		}
		if (!"".equals(Sidx)&&Sidx!=null) {
			sql.append(" ORDER BY ").append(Sidx).append(" ").append(Sord).append(" ");
		}
		return sql.toString();
	}
	/**
	 * 原sql没有where,此方法添加sql如: WHERE 1=1 {AND id  >='10' AND  sex  ='女'} limit 0 ,10
	 * @param sql  select * from t_wordbook
	 * @param pc 1
	 * @param ps 10
	 * @return  select * from t_wordbook WHERE 1=1 
	 * 			{AND id  >='10' AND  sex  ='女'} 
	 * 			limit 0 ,10
	 */
	public String toWhereLimitSql(StringBuffer sql,int pc,int ps) {
		sql.append(" WHERE 1=1 ");
		if (rules!=null) {
			sql.append(groupOp).append(" ");
			for(int i=0;i<rules.size();i++){
				sql.append(rules.get(i).toSql());
				if(i != rules.size()-1){
					sql.append(groupOp).append(" ");
				}
			}
		}
		if (!"".equals(Sidx)&&Sidx!=null) {
			sql.append(" ORDER BY ").append(Sidx).append(" ").append(Sord).append(" ");
		}
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		System.out.println(sql);
		return sql.toString();
	}
	
	/**
	 * 原sql有where,此方法添加sql如: {AND id  >='10' AND  sex  ='女'} limit 0 ,10
	 * @param sql  select * from t_office where office_isdel = 0 
	 * @param pc 1
	 * @param ps 10
	 * @return  select * from t_office where office_isdel = 0 
	 * 			{AND id  >='10' AND  sex  ='女'} 
	 * 			limit 0 ,10
	 */
	public String toLimitSql(StringBuffer sql,int pc,int ps) {
		if (rules!=null) {
			sql.append(" ").append(groupOp).append(" ");
			for(int i=0;i<rules.size();i++){
				sql.append(rules.get(i).toSql());
				if(i != rules.size()-1){
					sql.append(groupOp).append(" ");
				}
			}
		}
		if (!"".equals(Sidx)&&Sidx!=null) {
			sql.append(" ORDER BY ").append(Sidx).append(" ").append(Sord).append(" ");
		}
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		return sql.toString();
	}
}

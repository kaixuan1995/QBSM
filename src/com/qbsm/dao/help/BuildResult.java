package com.qbsm.dao.help;

/**
 * 构建SQL语句的结果类
 * 
 * @author xieguoping
 * 
 */
public class BuildResult {

	private String sql;

	private Object[] params;

	public BuildResult() {
	}

	public BuildResult(String sql, Object[] params) {
		this.sql = sql;
		this.params = params;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "SqlResult [sql=" + sql + ", params=" + this.showParams() + "]";
	}

	private String showParams() {
		if (params != null && params.length > 0) {
			StringBuilder result = new StringBuilder("{");
			for (int i = 0; i < params.length; i++) {
				result.append(params[i] + ",");
			}
			result.deleteCharAt(result.lastIndexOf(","));
			result.append("}");
			return result.toString();
		}
		return "";

	}
}

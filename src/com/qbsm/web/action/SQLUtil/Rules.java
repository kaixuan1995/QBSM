package com.qbsm.web.action.SQLUtil;

public class Rules {
	private String Field;
	private String Op;
	private String Data;
	
	protected String getField() {
		return Field;
	}
	protected void setField(String field) {
		Field = field;
	}
	protected String getOp() {
		return Op;
	}
	protected void setOp(String op) {
		Op = op;
	}
	protected String getData() {
		return Data;
	}
	protected void setData(String data) {
		Data = data;
	}
	@Override
	public String toString() {
		return "Rules [Field=" + Field + ", Op=" + Op + ", Data=" + Data + "]";
	}
	
	protected String toSql() {
		StringBuffer sql = new StringBuffer(" ");
		sql.append(Field).append(" ");
		if (Op.equals("eq")) {// 相等
			sql.append(" ='").append(Data).append("' ");
		} else if (Op.equals("ne")) {// 不相等
			sql.append(" !='").append(Data).append("' ");
		} else if (Op.equals("lt")) {// 小于
			sql.append(" <'").append(Data).append("' ");
		} else if (Op.equals("le")) {// 小于等于
			sql.append(" <='").append(Data).append("' ");
		} else if (Op.equals("gt")) {// 大于
			sql.append(" >'").append(Data).append("' ");
		} else if (Op.equals("ge")) {// 大于等于
			sql.append(" >='").append(Data).append("' ");
		} else if (Op.equals("cn")) {// 包含
			sql.append(" LIKE '%").append(Data).append("%' ");
		} else if (Op.equals("nc")) {// 不包含
			sql.append(" NOT LIKE '%").append(Data).append("%' ");
		} else if (Op.equals("ew")) {// 以...结束
			sql.append(" LIKE '%").append(Data).append("' ");
		} else if (Op.equals("en")) {// 不以...结束
			sql.append(" NOT LIKE '%").append(Data).append("' ");
		} else if (Op.equals("bw")) {// 以...开始
			sql.append(" LIKE '").append(Data).append("%' ");
		} else if (Op.equals("bn")) {// 不以...开始
			sql.append(" NOT LIKE '").append(Data).append("%' ");
		} else if (Op.equals("in")) {// 在...之内
			String[] data = Data.split(",");
			sql.append(" IN (");
			for (int i = 0; i < data.length; i++) {
				sql.append("'").append(data[i]).append("'");
				if (i != data.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
		} else if (Op.equals("ni")) {// 不在...之内
			String[] data = Data.split(",");
			sql.append(" NOT IN (");
			for (int i = 0; i < data.length; i++) {
				sql.append("'").append(data[i]).append("'");
				if (i != data.length - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
		} else {
			sql.append(" LIKE '%").append(Data).append("%' ");
		}
		return sql.toString();
	}
}

package com.qbsm.dao.help;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qbsm.dao.util.ResetBeanUtils;

public class ResultSetToBeans {

	/**
	 * 将数据绑定成对象的集合
	 * 
	 * @param rs
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> bindDataToBeans(ResultSet rs, Class<?> clazz) {
		List list = resultSetToList(rs);
		return list == null ? null : ResetBeanUtils.copy(list, clazz);
	}

	private static List<Map<String, Object>> resultSetToList(ResultSet rs) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			ResultSetMetaData rsMeta = rs.getMetaData();
			while (rs.next()) {
				map = new HashMap<String, Object>();
				for (int i = 0; i < rsMeta.getColumnCount(); ++i) {
					String columnName = rsMeta.getColumnName(i + 1);
					map.put(columnName, rs.getObject(columnName));
				}
				result.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result.isEmpty() ? null : result;
	}
}

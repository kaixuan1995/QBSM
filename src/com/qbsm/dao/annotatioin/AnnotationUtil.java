package com.qbsm.dao.annotatioin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AnnotationUtil {

	/**
	 * 获得类级别中表名的注解
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getAnnotationTable(Class clazz) {

		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);// 获取Table注解
			return table.value();
		}
		return null;
	}

	/**
	 * 获得类中所有注解了外键的字段
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getForeignColumns(Class clazz) {
		List<Field> foreignColumn = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ForeignColumn.class)) {
				foreignColumn.add(field);
			}
		}
		return foreignColumn;
	}

	/**
	 * 获得类中间接表字段的集合
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> getInderectColumns(Class clazz) {
		List<Field> InderectColumn = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(InderectColumn.class)) {
				InderectColumn.add(field);
			}
		}
		return InderectColumn;
	}

	/**
	 * 获得有主键注解的属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static Field getPrimaryField(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(PrimaryColumn.class)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获得属性的注解主键列名,没有设置时返回属性名
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getPrimaryColumnName(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(PrimaryColumn.class)) {
				String ColumnName = field.getAnnotation(PrimaryColumn.class).value();
				if (ColumnName.equalsIgnoreCase("")) {
					return field.getName();
				} else {
					return ColumnName;
				}
			}
		}
		return null;
	}

	/**
	 * 获得属性的注解主键列名,没有设置时返回属性名
	 * 
	 * @param field
	 * @return
	 */
	public static String getPrimaryColumnName(Field field) {
		if (field.isAnnotationPresent(PrimaryColumn.class)) {
			String ColumnName = field.getAnnotation(PrimaryColumn.class).value();
			if (ColumnName.equalsIgnoreCase("")) {
				return field.getName();
			} else {
				return ColumnName;
			}
		}
		return null;
	}

	/**
	 * 获得属性注解的外键表名和列名。其中没有设置列名时默认返回属性名。
	 * 
	 * @param field
	 * @return String[0] 表名 String[1]列名
	 */
	public static String[] getForeignColumnMapping(Field field) {
		if (field.isAnnotationPresent(ForeignColumn.class)) {
			ForeignColumn foreign = field.getAnnotation(ForeignColumn.class);
			String table = foreign.Table();
			String ColumnName = foreign.ColumnName();
			if (ColumnName.equalsIgnoreCase("")) {
				return new String[] { table, field.getName() };
			} else {
				return new String[] { table, ColumnName };
			}
		}
		return null;
	}

	/**
	 * 获得属性注解的中间表名和列名。其中没有设置列名时默认返回属性名。
	 * 
	 * @param field
	 * @return String[0] 表名 String[1]列名
	 */
	public static String[] getInderectColumnMapping(Field field) {
		if (field.isAnnotationPresent(InderectColumn.class)) {
			InderectColumn foreign = field.getAnnotation(InderectColumn.class);
			String table = foreign.Table();
			String ColumnName = foreign.ColumnName();
			if (ColumnName.equalsIgnoreCase("")) {
				return new String[] { table, field.getName() };
			} else {
				return new String[] { table, ColumnName };
			}
		}
		return null;
	}

	/**
	 * 获得属性的注解的列名
	 * 
	 * @param field
	 * @return
	 */
	public static String getColumnName(Field field) {
		if (field.isAnnotationPresent(Column.class)) {
			return field.getAnnotation(Column.class).value();
		}
		return null;
	}

	/**
	 * 类是否含有TableAnnotation注解，存在返回true，不存在返回false
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTableAnnotation(Class clazz) {
		if (clazz.isAnnotationPresent(Table.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断属性是否含有主键注解
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isHasPrimaryAnnotation(Field field) {
		if (field.isAnnotationPresent(PrimaryColumn.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得属性是否含有外键注解
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isHasForeignAnnotation(Field field) {
		if (field.isAnnotationPresent(ForeignColumn.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得属性是否含有中间表注解
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isHasInderectAnnotation(Field field) {
		if (field.isAnnotationPresent(InderectColumn.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断属性是否含有列注解
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isHasColumnAnnotation(Field field) {
		if (field.isAnnotationPresent(Column.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得类属性名和数据库表的映射关系
	 * <p>
	 * key:属性名 ;value：数据库表列名
	 * 
	 * @param clazz
	 * @return
	 */
	public static Map NameMapping(Class clazz) {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (AnnotationUtil.isHasPrimaryAnnotation(field)) {
				map.put(field.getName(), AnnotationUtil.getPrimaryColumnName(field));
			} else if (AnnotationUtil.isHasColumnAnnotation(field)) {
				map.put(field.getName(), AnnotationUtil.getColumnName(field));
			} else {
				map.put(field.getName(), field.getName());
			}
		}
		return map;
	}
}

package com.qbsm.dao.help;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.qbsm.dao.annotatioin.AnnotationUtil;
import com.qbsm.dao.util.ClassUtil;

@SuppressWarnings({ "unused", "rawtypes" })
public class SQLBuildUtil implements SQLUtil {

	@Override
	public BuildResult buildSaveSQL(Object Po) {
		if (Po == null) {
			throw new RuntimeException("参数Po不能为null");
		}

		// 构建完成的sql语句
		StringBuilder sql = new StringBuilder("insert into ");

		// 构建完成的sql语句对应的参数
		List<Object> params = new ArrayList<Object>();

		Class clazz = Po.getClass();
		if (!AnnotationUtil.isHasTableAnnotation(clazz)) {
			throw new RuntimeException("构建插入SQL语句失败! 原因：该类不存在@Table注解!");
		}
		sql.append(AnnotationUtil.getAnnotationTable(clazz) + "(");
		Field[] fields = clazz.getDeclaredFields();
		Map nameMap = AnnotationUtil.NameMapping(clazz);
		try {
			for (Field field : fields) {
				Object value = PropertyUtils.getNestedProperty(Po,
						field.getName());
				if (value != null) {
					sql.append(nameMap.get(field.getName()) + ",");
					params.add(value);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(clazz.getName() + "中存在属性未定义set/get方法！",
					e);
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")").append("values(");
		for (Object temp : params) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");

		return new BuildResult(sql.toString(), params.toArray());
	}

	@Override
	public BuildResult buildUpdateSQL(Object Po) {
		if (Po == null) {
			throw new RuntimeException("参数obj不能为null");
		}
		// 构建完成的sql语句
		StringBuilder sql = new StringBuilder("update ");
		// 构建完成的sql语句对应的参数
		List<Object> params = new ArrayList<Object>();
		Class clazz = Po.getClass();
		if (!AnnotationUtil.isHasTableAnnotation(clazz)) {// 判断是否存在Table注解
			throw new RuntimeException("构建更新SQL语句失败! 原因：该类不存在@Table注解!");
		}
		sql.append(AnnotationUtil.getAnnotationTable(clazz) + " set ");
		Map nameMap = AnnotationUtil.NameMapping(clazz);
		try {
			for (Field field : clazz.getDeclaredFields()) {
				Object value = PropertyUtils.getNestedProperty(Po,
						field.getName());
				if (value != null) {
					sql.append(nameMap.get(field.getName()) + "=?,");
					params.add(value);
				}
			}
			sql.deleteCharAt(sql.lastIndexOf(","));
			Field primaryField = AnnotationUtil.getPrimaryField(clazz);
			Object value = PropertyUtils.getNestedProperty(Po,
					primaryField.getName());
			if (value == null) {
				throw new RuntimeException("构建更新SQL语句失败! 原因：主键未设值!");
			}
			sql.append(" where " + nameMap.get(primaryField.getName()) + "=?");
			params.add(value);

			if (params.size() <= 1) {
				throw new RuntimeException("请设置要更新的属性值！");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(clazz.getName() + "中存在属性未定义set/get方法！",
					e);
		}
		return new BuildResult(sql.toString(), params.toArray());
	}

	@Override
	public BuildResult buildDeleteSQL(Class PoClass, Integer[] ids) {
		if (PoClass == null) {
			throw new RuntimeException("clazz不能为null");
		}
		if (ids == null || ids.length == 0) {
			throw new RuntimeException("ids不能为null或空!");
		}
		// 构建完成的sql语句
		StringBuilder sql = new StringBuilder("delete from ");
		// 构建完成的sql语句对应的参数
		List<Object> params = new ArrayList<Object>();

		if (!AnnotationUtil.isHasTableAnnotation(PoClass)) {// 判断是否存在Table注解
			throw new RuntimeException("构建删除SQL语句失败! 原因：该类不存在@Table注解!");
		}
		sql.append(AnnotationUtil.getAnnotationTable(PoClass) + " where ");
		sql.append(AnnotationUtil.getPrimaryColumnName(PoClass) + " in(");
		for (Integer id : ids) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		return new BuildResult(sql.toString(), params.toArray());
	}

	@Override
	public BuildResult buildDeleteSQL(Object Po) {
		if (Po == null) {
			throw new RuntimeException("参数obj不能为null");
		}
		// 构建完成的sql语句
		StringBuilder sql = new StringBuilder("delete from ");
		// 构建完成的sql语句对应的参数
		List<Object> params = new ArrayList<Object>();

		Class clazz = Po.getClass();
		if (!AnnotationUtil.isHasTableAnnotation(clazz)) {// 判断是否存在TableAnnotation注解
			throw new RuntimeException("构建删除SQL语句失败! 原因：该类不存在@Table注解!");
		}
		sql.append(AnnotationUtil.getAnnotationTable(clazz) + " where 1=1");
		Field primaryField = AnnotationUtil.getPrimaryField(clazz);
		Map nameMap = AnnotationUtil.NameMapping(clazz);
		try {
			if (PropertyUtils.getNestedProperty(Po, primaryField.getName()) == null) {
				throw new RuntimeException("构建删除SQL语句失败! 原因：主键未设值!");
			}
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Object value = PropertyUtils.getNestedProperty(Po,
						field.getName());
				if (value != null) {
					sql.append(" and " + nameMap.get(field.getName()) + "=?");
					params.add(value);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new BuildResult(sql.toString(), params.toArray());
	}

	// -----------------构建查询语句---------------------------------------------------------------------

	@Override
	public BuildResult buildQuerySQL(Class PoClass, Integer[] ids) {
		if (!AnnotationUtil.isHasTableAnnotation(PoClass)) {// 判断是否存在TableAnnotation注解
			throw new RuntimeException("构建SQL语句失败! 原因：该类不存在@Table注解!");
		}
		Field primaryField = AnnotationUtil.getPrimaryField(PoClass);
		if (primaryField == null) {
			throw new RuntimeException(
					"构建SQL语句失败! 原因：该类不存在@PrimaryAnnotation注解!");
		}
		StringBuilder sql = new StringBuilder("select * from ");
		String table = AnnotationUtil.getAnnotationTable(PoClass);
		sql.append(table);
		sql.append(" where 1=1 and ");
		sql.append(AnnotationUtil.getPrimaryColumnName(primaryField));
		sql.append(" in(");
		for (Integer id : ids) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(")");
		return new BuildResult(sql.toString(), ids);
	}

	// 构建sql中需要连接的所有的表
	private Set<String> tables = null;
	// 表与表间的连接条件
	private Set<String> connectives = null;

	@Override
	public BuildResult buildQuerySQL(Object... entitys) {
		tables = new HashSet<String>();
		connectives = new HashSet<String>();
		// 获得类中的注解的所有的表
		this.getAllTables(entitys);
		// 获得外键连接条件
		this.buildForeignConnective(ClassUtil.getClass(entitys));
		// 获得主键的连接条件
		this.buildInderectConnetionPart(ClassUtil.getClass(entitys));
		// 构建select部分的SQL语句
		StringBuilder sql = this.buildSelectPart(tables);
		for (String con : connectives) {// 构建表和表之间的连接条件
			sql.append(" and ").append(con);
		}
		// 构建筛选条件部分语句及参数
		BuildResult buildResult = this.buildWhereConnective(entitys);
		sql.append(buildResult.getSql());
		buildResult.setSql(sql.toString());
		return buildResult;
	}

	@Override
	public BuildResult buildQueryDataCountSQL(Object... Pos) {
		tables = new HashSet<String>();
		connectives = new HashSet<String>();
		// 获得类中的注解的所有的表
		this.getAllTables(Pos);
		// 获得外键连接条件
		this.buildForeignConnective(ClassUtil.getClass(Pos));
		// 获得主键的连接条件
		this.buildInderectConnetionPart(ClassUtil.getClass(Pos));
		// 构建select部分的SQL语句
		StringBuilder sql = new StringBuilder("select count(*) from ");
		for (String table : tables) {
			sql.append(table).append(",");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" where 1=1");

		for (String con : connectives) {// 构建表和表之间的连接条件
			sql.append(" and ").append(con);
		}
		// 构建筛选条件部分语句及参数
		BuildResult buildResult = this.buildWhereConnective(Pos);

		sql.append(buildResult.getSql());
		buildResult.setSql(sql.toString());
		return buildResult;
	}

	/**
	 * 获得所有类中的中间表连接条件
	 * 
	 * @param PoClass
	 */
	private void buildInderectConnetionPart(Class[] PoClass) {
		for (int i = 0; i < PoClass.length; i++) {
			for (int j = i + 1; j < PoClass.length; j++) {
				this.getInderectConnective(PoClass[i], PoClass[j]);
			}
		}
	}

	/**
	 * 获得获得两个类的中间表连接条件
	 * 
	 * @param frist
	 * @param second
	 */
	private void getInderectConnective(Class frist, Class second) {
		List<Field> fristFields = AnnotationUtil.getInderectColumns(frist);// 类中注解了中间表的属性
		List<Field> secondFields = AnnotationUtil.getInderectColumns(second);// 类中注解了中间表的属性
		for (Field fristField : fristFields) {
			String[] fristInderectColumn = AnnotationUtil
					.getInderectColumnMapping(fristField);
			for (Field secondField : secondFields) {
				String[] secondInderectColumn = AnnotationUtil
						.getInderectColumnMapping(secondField);
				if (fristInderectColumn[0]
						.equalsIgnoreCase(secondInderectColumn[0])) {
					// 添加连接条件
					connectives.add(fristInderectColumn[0] + "."
							+ fristInderectColumn[1] + "="
							+ AnnotationUtil.getAnnotationTable(frist) + "."
							+ fristField.getName());
					connectives.add(fristInderectColumn[0] + "."
							+ secondInderectColumn[1] + "="
							+ AnnotationUtil.getAnnotationTable(second) + "."
							+ secondField.getName());
					tables.add(fristInderectColumn[0]);// 添加表名
				}
			}
		}
	}

	/**
	 * 构建筛选条件部分语句及参数
	 * 
	 * @param Pos
	 * @return
	 */
	private BuildResult buildWhereConnective(Object[] Pos) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		try {
			for (Object obj : Pos) {
				Field[] fields = obj.getClass().getDeclaredFields();
				Map nameMap = AnnotationUtil.NameMapping(obj.getClass());
				for (Field field : fields) {
					Object value = PropertyUtils.getNestedProperty(obj,
							field.getName());
					if (value != null) {
						sql.append(" and " + nameMap.get(field.getName())
								+ "=?");
						params.add(value);
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("类中存在属性未定义set/get方法！", e);
		}
		return new BuildResult(sql.toString(), params.toArray());
	}

	/**
	 * 
	 * 获得两个类之间外键的连接条件
	 * 
	 * @param frist
	 *            第一个类
	 * @param second
	 *            第二个类
	 */
	private void getReferenceConnective(Class frist, Class second) {
		List<Field> fristForeignFields = AnnotationUtil
				.getForeignColumns(frist);// 第一个类中注解为外键的属性
		List<Field> secondForeignFields = AnnotationUtil
				.getForeignColumns(second);// 第一个类中注解为外键的属性
		String fristTable = AnnotationUtil.getAnnotationTable(frist);// 第一个类注解的表名
		String secondTable = AnnotationUtil.getAnnotationTable(second);// 第二个类注解的表名
		for (Field fristForeignField : fristForeignFields) {
			String[] foreign = AnnotationUtil
					.getForeignColumnMapping(fristForeignField);
			if (secondTable.equalsIgnoreCase(foreign[0])) {
				connectives.add(foreign[0] + "." + foreign[1] + "="
						+ fristTable + "." + fristForeignField.getName());// 添加frist和second实体间外键的连接条件
			}
		}
		for (Field secondForeignField : secondForeignFields) {
			String[] foreign = AnnotationUtil
					.getForeignColumnMapping(secondForeignField);
			if (fristTable.equalsIgnoreCase(foreign[0])) {
				connectives.add(foreign[0] + "." + foreign[1] + "="
						+ secondTable + "." + secondForeignField.getName());// 添加frist和second实体间外键的连接条件
			}
		}
	}

	/**
	 * 获得所有类间的外键连接条件
	 * 
	 * @param PoClass
	 */
	private void buildForeignConnective(Class[] PoClass) {
		for (int i = 0; i < PoClass.length; i++) {
			for (int j = i + 1; j < PoClass.length; j++) {
				this.getReferenceConnective(PoClass[i], PoClass[j]);
			}
		}
	}

	/**
	 * 获得类级别的所有表名
	 * 
	 * @param Pos
	 */
	private void getAllTables(Object[] Pos) {
		for (Object obj : Pos) {
			tables.add(AnnotationUtil.getAnnotationTable(obj.getClass()));
		}
	}

	/**
	 * 构建select部分语句
	 * 
	 * @param tables
	 * @return
	 */
	private StringBuilder buildSelectPart(Set<String> tables) {
		StringBuilder sql = new StringBuilder("select * from ");
		for (String table : tables) {
			sql.append(table).append(",");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" where 1=1");
		return sql;
	}
}

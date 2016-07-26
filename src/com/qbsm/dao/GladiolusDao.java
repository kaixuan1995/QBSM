package com.qbsm.dao;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface GladiolusDao {

	/*
	 * ----------------------------------查询方法------------------------------------
	 */
	/**
	 * 查询方法,通过传入Po的主键值和Po的字节码进行查询.没有查询到对应结果时返回null.
	 * 
	 * @param id
	 *            Po的主键值
	 * @param PoClass
	 *            Po的字节码,该Po需要进行相应的注解
	 * @return
	 */
	public <T> T getById(Integer id, Class PoClass);

	/**
	 * 查询方法,通过传入Po的主键值和Po的字节码进行查询.没有查询到对应结果时返回null.
	 * 
	 * @param ids
	 *            Po的主键值
	 * @param PoClass
	 *            Po的字节码,该Po需要进行相应的注解
	 * @return
	 */
	public <T> List<T> getByIds(Integer[] ids, Class PoClass);

	/**
	 * 查询方法,通过传入Po的主键值和Po的字节码进行查询.没有查询到对应结果时返回null.
	 * 
	 * @param Po
	 * @return
	 */
	public <T> T queryForObject(Object Po);

	/**
	 * 返回一个Object类型的基本数据类型,注意该方法只能查询一个列的数据
	 * 
	 * @param sql
	 *            例如：select count(*) from t_table或者 select id from t_table
	 * @param params
	 *            传入参数,对应SQL语句中的占位符，可以为null
	 *            格式：{1,"张三"}或者{{"1","张三"},{"2","李四"}};
	 * @return
	 */
	public Object queryForObject(String sql, Object[] params);

	/**
	 * 返回一个封装好的对象
	 * 
	 * @param sql
	 * @param params
	 *            传入参数,对应SQL语句中的占位符，可以为null
	 *            格式：{"xiao22","123"}或者{{"xiao22","123"},{"xiao33","110"}};
	 * @param encapClass
	 *            要封装成的对象字节码
	 * @return
	 */
	public <T> T queryForObject(String sql, Object[] params, Class encapClass);

	/**
	 * 根据Po对象属性的值查询符合条件的记录
	 * 
	 * @param po
	 *            Po类需要进行相应的注解
	 * @return
	 */
	public <T> List<T> queryForListByPo(Object po);

	/**
	 * 根据Po对象属性的值查询符合条件的记录(带分页的)
	 * 
	 * @param po
	 *            Po类需要进行相应的注解
	 * @param start
	 *            开始的位置
	 * @param MaxSize
	 *            最多查询几条
	 * @return
	 */
	public <T> List<T> queryForListByPo(Object po, Integer start, Integer MaxSize);

	/**
	 * 传入Vo对象后会根据配置拆解为对应的Po,然后根据Po对象属性的值查询符合条件的记录
	 * 
	 * @param vo
	 *            Vo类需要在gladiolus-beans.xml文件中配置相应的属性
	 * @return
	 */
	public <T> List<T> queryForListByVo(Object vo);

	/**
	 * 传入Vo对象后会根据配置拆解为对应的Po,然后根据Po对象属性的值查询符合条件的记录
	 * 
	 * @param encapClass
	 *            要封装成的对象
	 * @param vo
	 *            Vo类需要在gladiolus-beans.xml文件中配置相应的属性
	 * @param start
	 *            开始的位置
	 * @param MaxSize
	 *            最多查询几条
	 * @return
	 */
	public <T> List<T> queryForListByVo(Object vo, Integer start, Integer MaxSize);

	/**
	 * 将SQL查询出的记录封装为encapClass参数指定的对象
	 * (该封装原则为:当SQL查询的列名与encapClass指定类的属性一致时才能赋值,不一致时直接忽略。
	 * 当属性名和列名完全不一样时返回结果集对象的属性值全为空。)
	 * 
	 * @param sql
	 * @param encapClass
	 *            要封装成的对象
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Class encapClass);

	/**
	 * 将SQL查询出的记录封装为encapClass参数指定的对象
	 * (该封装原则为:当SQL查询的列名与encapClass指定类的属性一致时才能赋值,不一致时直接忽略。
	 * 当属性名和列名完全不一样时返回结果集对象的属性值全为空。)
	 * 
	 * @param sql
	 * @param params
	 * @param encapClass
	 *            要封装成的对象
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Object[] params, Class encapClass);

	/**
	 * 根据多个存在关联的Po对象的属性值查询符合条件的记录封装为encapClass参数指定的对象
	 * (该封装原则为:当SQL查询的列名与encapClass指定类的属性一致时才能赋值,不一致时直接忽略。
	 * 当属性名和列名完全不一样时返回结果集对象的属性值全为空。)
	 * 
	 * @param encapClass
	 *            要封装成的对象
	 * @param po
	 * @return
	 */
	public <T> List<T> queryForList(Class encapClass, Object... po);

	/**
	 * 根据一个或多个存在关联的Po对象的属性值查询符合条件的记录封装为encapClass参数指定的对象
	 * (该封装原则为:当SQL查询的列名与encapClass指定类的属性一致时才能赋值,不一致时直接忽略。
	 * 当属性名和列名完全不一样时返回结果集对象的属性值全为空。)
	 * 
	 * @param encapClass
	 *            要封装成的对象
	 * @param start
	 *            开始的位置
	 * @param MaxSize
	 *            最多查询几条
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public <T> List<T> queryForList(Class encapClass, Integer start, Integer MaxSize, Object... po);

	/**
	 * 根据一个或多个存在关联的Po对象的属性值查询符合条件的记录条数
	 * 
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public int getDataCount(Object... po);

	/**
	 * 查询符合条件的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getDataCount(String sql, Object[] params);

	/**
	 * 查询符合条件的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getDataCountByVo(Object vo);

	/**
	 * 是否存在po属性对应的记录
	 * 
	 * @param po
	 * @return
	 */
	public boolean containsEntity(Object po);

	/*
	 * ----------------------------------事务方法------------------------------------
	 */
	/**
	 * 开启事务
	 */
	public void beginTransaction();

	/**
	 * 回滚事务
	 */
	public void rollbackTransaction();

	/**
	 * 提交事务
	 */
	public void commitTransaction();

	/*
	 * ----------------------------------更新方法------------------------------------
	 */

	/**
	 * 通过Po向数据库插入一条记录
	 * 
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public boolean save(Object po);

	/**
	 * 通过一个Vo,解析成相对应的一个或多个Po,向数据库里插入记录
	 * 
	 * @param vo
	 *            Vo类需要在gladiolus-beans.xml文件中配置相应的属性
	 * @return
	 */
	public boolean saveByVo(Object vo);

	/**
	 * 插入一条数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean save(String sql, Object[] params);

	/**
	 * 根据PO对象删除一条记录,该PO对象注解为主键的属性值不能为空
	 * 
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public boolean delete(Object po);

	/**
	 * 根据主键ID删除参数PoClass指定的记录
	 * 
	 * @param id
	 * @param PoClass
	 *            Po的字节码，该类需要相应的注解
	 * @return
	 */
	public boolean delete(Integer id, Class PoClass);

	/**
	 * 根据主键ID删除参数PoClass指定的记录
	 * 
	 * @param ids
	 * @param PoClass
	 *            Po的字节码，该类需要相应的注解
	 * @return
	 */
	public boolean delete(Integer[] ids, Class PoClass);

	/**
	 * 根据Po更新记录,该Po对象中注解为主键的属性值不能为空
	 * 
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public boolean update(Object po);

	/**
	 * 通过一个Vo,解析成相对应的一个或多个Po,更新数据库中的记录
	 * 
	 * @param vo
	 *            Vo类需要在gladiolus-beans.xml文件中配置相应的属性
	 * @return
	 */
	public boolean updateByVo(Object vo);

	/**
	 * 更新记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql, Object[] params);

	/**
	 * 批量更新
	 * 
	 * @param sql
	 * @param params
	 * @param batchCount
	 *            每批处理的数据数
	 * @return
	 */
	public boolean updateBatch(String sql, Object[][] params, int batchCount);

	/**
	 * 向数据库中插入一条数据并返回自增的ID
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Integer getGeneratedKeyByInsert(String sql, Object[] params);

	/**
	 * 通过Po对象向数据库中插入一条数据并返回自增的ID
	 * 
	 * @param po
	 *            Po对象需要相应的注解
	 * @return
	 */
	public Integer getGeneratedKeyByInsert(Object po);

}

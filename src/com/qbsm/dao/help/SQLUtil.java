package com.qbsm.dao.help;

@SuppressWarnings("rawtypes")
public interface SQLUtil {

	public abstract BuildResult buildSaveSQL(Object Po);

	public abstract BuildResult buildUpdateSQL(Object Po);

	/*---------------------------------------------------------*/

	public abstract BuildResult buildDeleteSQL(Class clazz, Integer[] ids);

	public abstract BuildResult buildDeleteSQL(Object Po);

	/*---------------------------------------------------------*/
	public abstract BuildResult buildQuerySQL(Class PoClass, Integer[] ids);

	public abstract BuildResult buildQuerySQL(Object... Po);

	public abstract BuildResult buildQueryDataCountSQL(Object... Po);

}
package com.qbsm.dao.annotatioin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xieguoping
 */
@Target(ElementType.FIELD)
// 作用于属性
@Retention(RetentionPolicy.RUNTIME)
// 有效期为运行时
public @interface InderectColumn {

	String Table();//中间表

	String ColumnName()default "";//对应中间表中的列名
}

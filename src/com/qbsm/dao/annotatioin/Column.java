package com.qbsm.dao.annotatioin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xieguoping value:列名
 */
@Target(ElementType.FIELD)
// 作用于属性
@Retention(RetentionPolicy.RUNTIME)
// 有效期为运行时
public @interface Column {

	String value();

}

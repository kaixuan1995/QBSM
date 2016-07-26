package com.qbsm.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
//作用于属性
@Retention(RetentionPolicy.RUNTIME)
//有效期为运行时
public @interface ValidateRule {

	int maxLength() default -1;
	int minLength() default -1;
    boolean email() default false;
//    boolean date() default false;
    boolean noNull() default false;
}

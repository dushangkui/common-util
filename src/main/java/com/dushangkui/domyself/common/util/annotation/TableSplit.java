package com.dushangkui.domyself.common.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface TableSplit {
	//是否分表
	 public boolean split() default true;
	 
	 public String value() default "";

	 public String field() default "";
	 
	 //获取分表策略
	 public String strategy();
	 
}

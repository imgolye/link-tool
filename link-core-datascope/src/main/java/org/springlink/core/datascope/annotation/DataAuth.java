package org.springlink.core.datascope.annotation;

import org.springlink.core.datascope.constant.DataScopeConstant;
import org.springlink.core.datascope.enums.DataScopeEnum;

import java.lang.annotation.*;

/**
 * 数据权限定义
 *
 * @author GL
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataAuth {

	/**
	 * 资源编号
	 */
	String code() default "";

	/**
	 * 数据权限对应字段
	 */
	String column() default DataScopeConstant.DEFAULT_COLUMN;

	/**
	 * 数据权限规则
	 */
	DataScopeEnum type() default DataScopeEnum.ALL;

	/**
	 * 可见字段
	 */
	String field() default "*";

	/**
	 * 数据权限规则值域
	 */
	String value() default "";

}


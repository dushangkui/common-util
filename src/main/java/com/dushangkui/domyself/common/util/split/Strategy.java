package com.dushangkui.domyself.common.util.split;

import com.dushangkui.domyself.common.util.exception.ConvertException;

import java.util.Map;

public interface Strategy {
	public static final String TABLE_NAME="table_name";
	public static final String SPLIT_FIELD="split_field";
	public static final String EXECUTE_PARAM_DECLARE="execute_param_declare";
	public static final String EXECUTE_PARAM_VALUES="execute_param_values";
	/**
	 * 传入一个需要分表的表名，返回一个处理后的表名 
	 * Strategy必须包含一个无参构造器
	 * @param params
	 * @return
	 */
	public String convert(Map<String, Object> params) throws Exception;
}

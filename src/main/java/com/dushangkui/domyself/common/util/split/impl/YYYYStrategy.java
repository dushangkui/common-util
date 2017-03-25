package com.dushangkui.domyself.common.util.split.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.dushangkui.domyself.common.util.split.Strategy;

/**
 * 按年分表
 * @author dushangkui
 *
 */
public class YYYYStrategy implements Strategy{

	@Override
	public String convert(Map<String, Object> params) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		StringBuilder sb=new StringBuilder(params.get(Strategy.TABLE_NAME).toString());
		sb.append("_");
		sb.append(sdf.format(new Date()));
		return sb.toString();
	}
	
}
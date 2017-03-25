package com.dushangkui.domyself.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static final Log log=LogFactory.getLog(DateUtil.class);
	public static String getToday(){
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);
	}
	
	public static Date getDate(String date){
		return getDate(date, "yyyy-MM-dd");
	}
	public static Date getDate(String date, String pattern){
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			return sdf.parse(date);
		} catch (Exception e) {
			log.error("转换日期异常："+date, e);
			return null;
		}
	}
	
	public static String toString(Date date){
		return toString(date, "yyyy-MM-dd");
	}
	public static String toString(Date date,String pattern){
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			log.error("转换日期异常："+date, e);
			return null;
		}
	}
}

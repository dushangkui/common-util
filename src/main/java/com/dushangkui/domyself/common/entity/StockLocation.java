package com.dushangkui.domyself.common.entity;

public enum StockLocation {
	SH("sh", "上海"),
	SZ("sz", "深圳"),
	UNKNOW("unknow", "未知");
	private StockLocation(String code, String desc){
		this.code=code;
		this.desc=desc;
	}
	
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}

	private String code;
	private String desc;
}

package com.dushangkui.domyself.common.entity;

public enum StockTypeEnum {
	A("A", "主板A股"), 
	B("B", "主板B股"), 
	GEM("GEM", "创业板"), 
	SEM("SEM", "中小板"),
	UNKNOW("unknow", "未知");
	private StockTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
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

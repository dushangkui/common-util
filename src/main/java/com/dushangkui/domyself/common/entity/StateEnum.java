package com.dushangkui.domyself.common.entity;

public enum StateEnum {
	USE("U","使用"),
	ERASE("E","刪除");
	
	private StateEnum(String code, String desc) {
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

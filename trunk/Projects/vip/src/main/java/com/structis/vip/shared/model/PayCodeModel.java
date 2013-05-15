package com.structis.vip.shared.model;


public class PayCodeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String PAC_ID = "id";
	public static final String PAC_CODE = "code";
	public static final String PAC_NAME = "name";

	public Integer getId() {
		return get(PAC_ID);
	}

	public void setId(Integer id) {
		set(PAC_ID, id);
	}

	public String getCode() {
		return get(PAC_CODE);
	}

	public void setCode(String code) {
		set(PAC_CODE, code);
	}
	
	public String getName() {
		return get(PAC_NAME);
	}

	public void setName(String name) {
		set(PAC_NAME, name);
	}
}
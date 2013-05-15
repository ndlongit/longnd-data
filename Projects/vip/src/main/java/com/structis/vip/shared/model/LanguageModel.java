package com.structis.vip.shared.model;

public class LanguageModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String LAG_ID = "id";
	public static final String LAG_CODE = "code";
	public static final String LAG_NAME = "name";
	public static final String LAG_IS_DEFAULT = "isDefault";

	public Integer getId() {
		return get(LAG_ID);
	}

	public void setId(Integer id) {
		set(LAG_ID, id);
	}

	public String getCode() {
		return get(LAG_CODE);
	}

	public void setCode(String code) {
		set(LAG_CODE, code);
	}

	public String getName() {
		return get(LAG_NAME);
	}

	public void setName(String name) {
		set(LAG_NAME, name);
	}
	
	public Integer getIsDefault() {
		return get(LAG_IS_DEFAULT);
	}

	public void setIsDefault(Integer isDefault) {
		set(LAG_IS_DEFAULT, isDefault);
	}
}

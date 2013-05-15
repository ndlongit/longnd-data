package com.structis.vip.shared.model;

public class CategoryModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String CAT_ID = "id";	
	public static final String CAT_NAME = "name";	

	public Integer getId() {
		return get(CAT_ID);
	}

	public void setId(Integer id) {
		set(CAT_ID, id);
	}

	public String getName() {
		return get(CAT_NAME);
	}

	public void setName(String name) {
		set(CAT_NAME, name);
	}
	
}

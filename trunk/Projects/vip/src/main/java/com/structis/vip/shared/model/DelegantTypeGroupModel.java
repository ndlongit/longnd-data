package com.structis.vip.shared.model;

public class DelegantTypeGroupModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	public static final String DELEGANT_TYPE_GROUP_ID = "id";
	public static final String DELEGANT_TYPE_GROUP_GROUP = "group";
	public static final String DELEGANT_TYPE_GROUP_NAME = "name";

	
	public Integer getId() {
		return get(DELEGANT_TYPE_GROUP_ID);
	}

	public void setId(Integer id) {
		set(DELEGANT_TYPE_GROUP_ID, id);
	}

	public String getName() {
		return get(DELEGANT_TYPE_GROUP_NAME);
	}

	public void setName(String name) {
		set(DELEGANT_TYPE_GROUP_NAME, name);
	}

	public String getGroup() {
		return get(DELEGANT_TYPE_GROUP_GROUP);
	}

	public void setGroup(String code) {
		set(DELEGANT_TYPE_GROUP_GROUP, code);
	}
}

package com.structis.vip.shared.model;

public class DelegationTypeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	public static final String DELEGATION_TYPE_ID = "id";
	public static final String DELEGATION_TYPE_NAME = "name";
	public static final String DELEGATION_TYPE_DESCRIPTION = "description";

	public static final Integer PRINCIPLE_TYPE = 1;
	public static final Integer SUB_TYPE = 2;
	public static final Integer TEMPORARY_TYPE = 3;
	
	public Integer getId() {
		return get(DELEGATION_TYPE_ID);
	}

	public void setId(Integer id) {
		set(DELEGATION_TYPE_ID, id);
	}

	public String getName() {
		return get(DELEGATION_TYPE_NAME);
	}

	public void setName(String name) {
		set(DELEGATION_TYPE_NAME, name);
	}

	public String getDescription() {
		return get(DELEGATION_TYPE_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DELEGATION_TYPE_DESCRIPTION, description);
	}
}

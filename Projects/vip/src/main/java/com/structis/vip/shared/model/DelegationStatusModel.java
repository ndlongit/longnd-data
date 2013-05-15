package com.structis.vip.shared.model;

public class DelegationStatusModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	public static final String DELEGATION_STATUS_ID = "id";
	public static final String DELEGATION_STATUS_NAME = "name";
	public static final String DELEGATION_STATUS_DESCRIPTION = "description";

	public Integer getId() {
		return get(DELEGATION_STATUS_ID);
	}

	public void setId(Integer id) {
		set(DELEGATION_STATUS_ID, id);
	}

	public String getName() {
		return get(DELEGATION_STATUS_NAME);
	}

	public void setName(String name) {
		set(DELEGATION_STATUS_NAME, name);
	}

	public String getDescription() {
		return get(DELEGATION_STATUS_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DELEGATION_STATUS_DESCRIPTION, description);
	}
}

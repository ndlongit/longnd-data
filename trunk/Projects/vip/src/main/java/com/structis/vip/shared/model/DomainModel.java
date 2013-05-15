package com.structis.vip.shared.model;

public class DomainModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DOMAIN_ID = "id";
	public static final String DOMAIN_DESCRIPTION = "description";
	public static final String DOMAIN_NAME = "name";

	public Integer getId() {
		return get(DOMAIN_ID);
	}

	public void setId(Integer id) {
		set(DOMAIN_ID, id);
	}

	public String getDescription() {
		return get(DOMAIN_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DOMAIN_DESCRIPTION, description);
	}

	public String getName() {
		return get(DOMAIN_NAME);
	}

	public void setName(String name) {
		set(DOMAIN_NAME, name);
	}
}

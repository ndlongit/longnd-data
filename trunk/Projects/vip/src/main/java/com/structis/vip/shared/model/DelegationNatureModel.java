package com.structis.vip.shared.model;

public class DelegationNatureModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DELE_NATURE_ID = "id";
	public static final String DELE_NATURE_NAME = "name";
	public static final String DELE_NATURE_DESCRIPTION = "description";
	public static final String DELE_NATURE_ENTITE = "entite";

	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	public Integer getId() {
		return get(DELE_NATURE_ID);
	}

	public void setId(Integer id) {
		set(DELE_NATURE_ID, id);
	}

	public String getName() {
		return get(DELE_NATURE_NAME);
	}

	public void setName(String name) {
		set(DELE_NATURE_NAME, name);
	}

	public String getDescription() {
		return get(DELE_NATURE_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DELE_NATURE_DESCRIPTION, description);
	}

	public EntiteModel getEntite() {
		return get(DELE_NATURE_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(DELE_NATURE_ENTITE, entite);
	}
	
	public void removeUnusedOnList() {
		//this.setId(null);
		//this.setName(null);
		this.setDescription(null);
		this.setEntite(null);		
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
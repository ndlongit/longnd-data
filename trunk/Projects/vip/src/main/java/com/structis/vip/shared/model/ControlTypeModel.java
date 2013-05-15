package com.structis.vip.shared.model;


public class ControlTypeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String CON_ID = "id";
	public static final String CON_LABEL = "label";
	public static final String CON_DESCRIPTION = "description";
	public static final String CON_ENTITE = "entite";

	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	public Integer getId() {
		return get(CON_ID);
	}

	public void setId(Integer id) {
		set(CON_ID, id);
	}

	public String getLabel() {
		return get(CON_LABEL);
	}

	public void setLabel(String label) {
		set(CON_LABEL, label);
	}

	public EntiteModel getEntite() {
		return get(CON_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(CON_ENTITE, entite);
	}
	
	public String getDescription() {
		return get(CON_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(CON_DESCRIPTION, description);
	}

	public void removeUnusedOnList() {
		setEntite(null);
		setLabel(null);
		setLibelle(null);
		
	}
}
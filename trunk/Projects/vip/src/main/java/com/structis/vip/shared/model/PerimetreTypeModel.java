package com.structis.vip.shared.model;


public class PerimetreTypeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String PERIMETRE_TYPE_ID = "ptyId";
	public static final String PERIMETRE_TYPE_NAME = "name";
	public static final String PERIMETRE_TYPE_ENTITE = "entite";
	public static final String PERIMETRE_TYPE_IS_SUBDELEGABLE = "isSubdelegable";
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;
	
	public String getPtyId() {
		return get(PERIMETRE_TYPE_ID);
	}
	public void setPtyId(String id) {
		set(PERIMETRE_TYPE_ID, id);
	}
	public String getName() {
		return get(PERIMETRE_TYPE_NAME);
	}
	public void setName(String name) {
		set(PERIMETRE_TYPE_NAME, name);
	}
	public EntiteModel getEntite() {
		return get(PERIMETRE_TYPE_ENTITE);
	}
	public void setEntite(EntiteModel entite) {
		set(PERIMETRE_TYPE_ENTITE, entite);
	}
	public Integer getIsSubdelegable() {
		return get(PERIMETRE_TYPE_IS_SUBDELEGABLE);
	}

	public void setIsSubdelegable(Integer isSubdelegable) {
		set(PERIMETRE_TYPE_IS_SUBDELEGABLE, isSubdelegable);
	}
}
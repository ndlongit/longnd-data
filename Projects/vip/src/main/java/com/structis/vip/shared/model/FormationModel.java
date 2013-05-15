package com.structis.vip.shared.model;

import java.util.Date;

public class FormationModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String FOR_ID = "id";
	public static final String FOR_LABEL = "label";
	public static final String FOR_DESCRIPTION = "description";
	public static final String FOR_ENTITE = "entite";
	public static final String FOR_DATE = "date";

	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	public Integer getId() {
		return get(FOR_ID);
	}

	public void setId(Integer id) {
		set(FOR_ID, id);
	}

	public String getLabel() {
		return get(FOR_LABEL);
	}

	public void setLabel(String label) {
		set(FOR_LABEL, label);
	}

	public String getDescription() {
		return get(FOR_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(FOR_DESCRIPTION, description);
	}
	
	public EntiteModel getEntite() {
		return get(FOR_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(FOR_ENTITE, entite);
	}

	public Date getDate() {
		return get(FOR_DATE);
	}

	public void setDate(Date entite) {
		set(FOR_DATE, entite);
	}

}
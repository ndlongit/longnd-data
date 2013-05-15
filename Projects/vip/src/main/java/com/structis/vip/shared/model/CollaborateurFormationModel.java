package com.structis.vip.shared.model;

import java.util.Date;

public class CollaborateurFormationModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String CFO_ID = "id";
	public static final String CFO_DATE = "date";
	public static final String CFO_FORMATION = "formation";
	public static final String CFO_COLLABORATURE = "collaborateur";

	@SuppressWarnings("unused")
	private FormationModel formation;

	@SuppressWarnings("unused")
	private CollaborateurModel collaborateur;

	public Integer getId() {
		return get(CFO_ID);
	}

	public void setId(Integer id) {
		set(CFO_ID, id);
	}

	public Date getDate() {
		return get(CFO_DATE);
	}
	
	public void setDate(Date date) {
		set(CFO_DATE, date);
	}
	
	public FormationModel getFormation() {
		return get(CFO_FORMATION);
	}
	
	public void setFormation(FormationModel formation) {
		set(CFO_FORMATION, formation);
	}
	
	public CollaborateurModel getCollaborateur() {
		return get(CFO_COLLABORATURE);
	}
	
	public void setCollaborateur(CollaborateurModel col) {
		set(CFO_COLLABORATURE, col);
	}
}

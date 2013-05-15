package com.structis.vip.shared.model;


public class DelegantPerimetreModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DTP_ID = "id";
	public static final String DTP_DELEGANT = "delegant";
	public static final String DTP_PERIMETRE = "perimetre";
	

	@SuppressWarnings("unused")
	private CollaborateurModel delegantModel;

	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;

	public Integer getId() {
		return get(DTP_ID);
	}

	public void setId(Integer id) {
		set(DTP_ID, id);
	}

	public CollaborateurModel getDelegant() {
		return get(DTP_DELEGANT);
	}

	public void setDelegant(CollaborateurModel delegant) {
		set(DTP_DELEGANT, delegant);
	}

	public PerimetreModel getPerimetre() {
		return get(DTP_PERIMETRE);
	}

	public void setPerimetre(PerimetreModel perimetre) {
		set(DTP_PERIMETRE, perimetre);
	}
}

package com.structis.vip.shared.model;


public class DelegatairePerimetreModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DTP_ID = "id";
	public static final String DTP_DELEGATAIRE = "delegataire";
	public static final String DTP_PERIMETRE = "perimetre";
	

	@SuppressWarnings("unused")
	private CollaborateurModel delegataireModel;

	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;

	public Integer getId() {
		return get(DTP_ID);
	}

	public void setId(Integer id) {
		set(DTP_ID, id);
	}

	public CollaborateurModel getDelegataire() {
		return get(DTP_DELEGATAIRE);
	}

	public void setDelegataire(CollaborateurModel delegataire) {
		set(DTP_DELEGATAIRE, delegataire);
	}

	public PerimetreModel getPerimetre() {
		return get(DTP_PERIMETRE);
	}

	public void setPerimetre(PerimetreModel perimetre) {
		set(DTP_PERIMETRE, perimetre);
	}
}

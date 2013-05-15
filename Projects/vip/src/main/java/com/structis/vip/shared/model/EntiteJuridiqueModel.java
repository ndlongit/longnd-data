package com.structis.vip.shared.model;


public class EntiteJuridiqueModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	public static final String ENTITE_JURIDIQUE_ID = "id";
	public static final String ENTITE_JURIDIQUE_NAME = "name";
	public static final String ENTITE_JURIDIQUE_CAPITAL = "capital";
	public static final String ENTITE_JURIDIQUE_ADDRESS = "address";
	public static final String ENTITE_JURIDIQUE_REGISTRATION_ID = "registrationId";
	public static final String ENTITE_JURIDIQUE_REGISTRATION_ADDRESS = "registrationAddress";
	public static final String ENTITE_JURIDIQUE_STATUT = "statut";
	public static final String ENTITE_JURIDIQUE_ENTITE = "entite";
	public static final String ENTITE_JURIDIQUE_IS_DEFAULT = "isDefault";
	public static final String ENTITE_JURIDIQUE_COLLABORATEUR = "collaborateur";
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	public Integer getId() {
		return get(ENTITE_JURIDIQUE_ID);
	}

	public void setId(Integer id) {
		set(ENTITE_JURIDIQUE_ID, id);
	}

	public String getName() {
		return get(ENTITE_JURIDIQUE_NAME);
	}

	public void setName(String name) {
		set(ENTITE_JURIDIQUE_NAME, name);
	}

	public String getCapital() {
		return get(ENTITE_JURIDIQUE_CAPITAL);
	}

	public void setCapital(String capital) {
		set(ENTITE_JURIDIQUE_CAPITAL, capital);
	}

	public String getAddress() {
		return get(ENTITE_JURIDIQUE_ADDRESS);
	}

	public void setAddress(String address) {
		set(ENTITE_JURIDIQUE_ADDRESS, address);
	}

	public String getRegistrationId() {
		return get(ENTITE_JURIDIQUE_REGISTRATION_ID);
	}

	public void setRegistrationId(String registrationId) {
		set(ENTITE_JURIDIQUE_REGISTRATION_ID, registrationId);
	}

	public String getRegistrationAddress() {
		return get(ENTITE_JURIDIQUE_REGISTRATION_ADDRESS);
	}

	public void setRegistrationAddress(String registrationAddress) {
		set(ENTITE_JURIDIQUE_REGISTRATION_ADDRESS, registrationAddress);
	}

	public String getStatut() {
		return get(ENTITE_JURIDIQUE_STATUT);
	}

	public void setStatut(String statut) {
		set(ENTITE_JURIDIQUE_STATUT, statut);
	}

	public EntiteModel getEntite() {
		return get(ENTITE_JURIDIQUE_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(ENTITE_JURIDIQUE_ENTITE, entite);
	}
	
	public Integer getIsDefault() {
		return get(ENTITE_JURIDIQUE_IS_DEFAULT);
	}

	public void setIsDefault(Integer isDefault) {
		set(ENTITE_JURIDIQUE_IS_DEFAULT, isDefault);
	}
}
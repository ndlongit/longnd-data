package com.structis.vip.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.structis.vip.server.bean.domain.User;

public class PerimetreModel extends BaseModelDataActivable implements
		Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PERIMETRE_ID = "perId";
	public static final String PERIMETRE_NAME = "name";
	public static final String PERIMETRE_ENTITE = "entite";
	public static final String PERIMETRE_ENTITE_JURIDIQUE = "entiteJuridique";
	public static final String PERIMETRE_PARENT = "parent";
	public static final String PERIMETRE_TYPE = "type";
	public static final String PERIMETRE_LANGUAGE = "language";

	public static final String PERIMETRE_CITY = "city";
	public static final String PERIMETRE_ADDRESS = "address";
	public static final String PERIMETRE_CHANTIER_SEP = "chantierSEP";
	public static final String PERIMETRE_CHANTIER_ID = "chantierID";
	public static final String PERIMETRE_CHANTIER_START_DATE = "chantierStartDate";
	public static final String PERIMETRE_CHANTIER_PLANNED_DATE = "chantierPlannedEndDate";
	public static final String PERIMETRE_CHANTIER_END_DATE = "chantierEndDate";
	public static final String PERIMETRE_CHANTIER_TYPE = "chantierType";
	public static final String PERIMETRE_IS_DELETED = "isDeleted";
	public static final String PERIMETRE_ARGOS_NAME = "argosName";
	
	public static final String PERIMETRE_CHANTIER_NUMERO_PROJECT = "chantierNumeroDeProjet";
	public static final String PERIMETRE_CHANTIER_GROUPEMENT = "chantierGroupement";
	public static final String PERIMETRE_CHANTIER_PARTENAIRES = "chantierPartenaires";
	public static final String PERIMETRE_CREATED_BY = "createdBy";
	public static final String PERIMETRE_IS_SUBDELEGABLE = "isSubdelegable";
		
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	@SuppressWarnings("unused")
	private EntiteJuridiqueModel entiteJuridiqueModel;

	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;
	
	@SuppressWarnings("unused")
	private UserModel userModel;


	@SuppressWarnings("unused")
	private PerimetreTypeModel perimetreTypeModel;

	@SuppressWarnings("unused")
	private LanguageModel languageModel;

	@SuppressWarnings("unused")
	private ChantierTypeModel chantierTypeModel;
		

	public String getPerId() {
		return (String) get(PERIMETRE_ID);
	}

	public void setPerId(String id) {
		set(PERIMETRE_ID, (String) id);
	}

	public String getName() {
		return get(PERIMETRE_NAME);
	}

	public void setName(String name) {
		set(PERIMETRE_NAME, name);
	}

	public EntiteModel getEntite() {
		return get(PERIMETRE_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(PERIMETRE_ENTITE, entite);
	}
	
	public EntiteJuridiqueModel getEntiteJuridique() {
		return get(PERIMETRE_ENTITE_JURIDIQUE);
	}

	public void setEntiteJuridique(EntiteJuridiqueModel entite) {
		set(PERIMETRE_ENTITE_JURIDIQUE, entite);
	}

	public PerimetreModel getParent() {
		return get(PERIMETRE_PARENT);
	}

	public void setParent(PerimetreModel parent) {
		set(PERIMETRE_PARENT, parent);
	}

	public PerimetreTypeModel getType() {
		return get(PERIMETRE_TYPE);
	}

	public void setType(PerimetreTypeModel type) {
		set(PERIMETRE_TYPE, type);
	}

	public LanguageModel getLanguage() {
		return get(PERIMETRE_LANGUAGE);
	}

	public void setLanguage(LanguageModel language) {
		set(PERIMETRE_LANGUAGE, language);
	}

	// tdo
	public String getCity() {
		return get(PERIMETRE_CITY);
	}

	public void setCity(String city) {
		set(PERIMETRE_CITY, city);
	}

	public Integer getChantierSEP() {
		return get(PERIMETRE_CHANTIER_SEP);
	}

	public void setChantierSEP(Integer chantierSEP) {
		set(PERIMETRE_CHANTIER_SEP, chantierSEP);
	}

	public String getChantierID() {
		return get(PERIMETRE_CHANTIER_ID);
	}

	public void setChantierID(String chantierID) {
		set(PERIMETRE_CHANTIER_ID, chantierID);
	}

	public Date getChantierStartDate() {
		return get(PERIMETRE_CHANTIER_START_DATE);
	}

	public void setChantierStartDate(Date chantierStartDate) {
		set(PERIMETRE_CHANTIER_START_DATE, chantierStartDate);
	}

	public Date getChantierPlannedEndDate() {
		return get(PERIMETRE_CHANTIER_PLANNED_DATE);
	}

	public void setChantierPlannedEndDate(Date chantierPlannedEndDate) {
		set(PERIMETRE_CHANTIER_PLANNED_DATE, chantierPlannedEndDate);
	}

	public Date getChantierEndDate() {
		return get(PERIMETRE_CHANTIER_END_DATE);
	}

	public void setChantierEndDate(Date chantierEndDate) {
		set(PERIMETRE_CHANTIER_END_DATE, chantierEndDate);
	}

	public Integer getIsDeleted() {
		return get(PERIMETRE_IS_DELETED);
	}

	public void setIsDeleted(Integer isDeleted) {
		set(PERIMETRE_IS_DELETED, isDeleted);
	}

	public String getArgosName() {
		return get(PERIMETRE_ARGOS_NAME);
	}

	public void setArgosName(String argosName) {
		set(PERIMETRE_ARGOS_NAME, argosName);
	}

	public ChantierTypeModel getChantierType() {
		return get(PERIMETRE_CHANTIER_TYPE);
	}

	public void setChantierType(ChantierTypeModel chantierType) {
		set(PERIMETRE_CHANTIER_TYPE, chantierType);
	}
	
	public String getAddress() {
		return get(PERIMETRE_ADDRESS);
	}

	public void setAddress(String address) {
		set(PERIMETRE_ADDRESS, address);
	}
			
	public String getChantierNumeroDeProjet() {
		return get(PERIMETRE_CHANTIER_NUMERO_PROJECT);
	}

	public void setChantierNumeroDeProjet(String chantierNumeroDeProjet) {
		set(PERIMETRE_CHANTIER_NUMERO_PROJECT, chantierNumeroDeProjet);
	}

	public Integer getChantierGroupement() {
		return get(PERIMETRE_CHANTIER_GROUPEMENT);
	}

	public void setChantierGroupement(Integer chantierGroupement) {
		set(PERIMETRE_CHANTIER_GROUPEMENT,chantierGroupement);
	}

	public String getChantierPartenaires() {
		return get(PERIMETRE_CHANTIER_PARTENAIRES);
	}

	public void setChantierPartenaires(String chantierPartenaires) {
		set(PERIMETRE_CHANTIER_PARTENAIRES, chantierPartenaires);
	}

	public UserModel getCreatedBy() {
		return get(PERIMETRE_CREATED_BY);
	}

	public void setCreatedBy(UserModel createdBy) {
		set(PERIMETRE_CREATED_BY, createdBy);
	}
	
	public Integer getIsSubdelegable() {
		return get(PERIMETRE_IS_SUBDELEGABLE);
	}

	public void setIsSubdelegable(Integer isSubdelegable) {
		set(PERIMETRE_IS_SUBDELEGABLE, isSubdelegable);
	}
	
	public void removeUnusedOnList() {
		//setPerId(null);
		//setEntite(null);
		setEntiteJuridique(null);
		setParent(null);
		//setType(null);
		setLanguage(null);
		setCity(null);
		setChantierSEP(null);
		setChantierID(null);
		setChantierStartDate(null);
		setChantierPlannedEndDate(null);
		setChantierEndDate(null);
		setIsDeleted(null);
		setArgosName(null);
//		setChantierType(null);
		setAddress(null);
	}
	
}

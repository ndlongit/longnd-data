package com.structis.vip.shared.model;

import java.io.Serializable;
import java.util.Date;

public class PerimetreModel extends BaseModelDataActivable implements Serializable {

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
        return (String) this.get(PERIMETRE_ID);
    }

    public void setPerId(String id) {
        this.set(PERIMETRE_ID, id);
    }

    public String getName() {
        return this.get(PERIMETRE_NAME);
    }

    public void setName(String name) {
        this.set(PERIMETRE_NAME, name);
    }

    public EntiteModel getEntite() {
        return this.get(PERIMETRE_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(PERIMETRE_ENTITE, entite);
    }

    public EntiteJuridiqueModel getEntiteJuridique() {
        return this.get(PERIMETRE_ENTITE_JURIDIQUE);
    }

    public void setEntiteJuridique(EntiteJuridiqueModel entite) {
        this.set(PERIMETRE_ENTITE_JURIDIQUE, entite);
    }

    public PerimetreModel getParent() {
        return this.get(PERIMETRE_PARENT);
    }

    public void setParent(PerimetreModel parent) {
        this.set(PERIMETRE_PARENT, parent);
    }

    public PerimetreTypeModel getType() {
        return this.get(PERIMETRE_TYPE);
    }

    public void setType(PerimetreTypeModel type) {
        this.set(PERIMETRE_TYPE, type);
    }

    public LanguageModel getLanguage() {
        return this.get(PERIMETRE_LANGUAGE);
    }

    public void setLanguage(LanguageModel language) {
        this.set(PERIMETRE_LANGUAGE, language);
    }

    // tdo
    public String getCity() {
        return this.get(PERIMETRE_CITY);
    }

    public void setCity(String city) {
        this.set(PERIMETRE_CITY, city);
    }

    public Integer getChantierSEP() {
        return this.get(PERIMETRE_CHANTIER_SEP);
    }

    public void setChantierSEP(Integer chantierSEP) {
        this.set(PERIMETRE_CHANTIER_SEP, chantierSEP);
    }

    public String getChantierID() {
        return this.get(PERIMETRE_CHANTIER_ID);
    }

    public void setChantierID(String chantierID) {
        this.set(PERIMETRE_CHANTIER_ID, chantierID);
    }

    public Date getChantierStartDate() {
        return this.get(PERIMETRE_CHANTIER_START_DATE);
    }

    public void setChantierStartDate(Date chantierStartDate) {
        this.set(PERIMETRE_CHANTIER_START_DATE, chantierStartDate);
    }

    public Date getChantierPlannedEndDate() {
        return this.get(PERIMETRE_CHANTIER_PLANNED_DATE);
    }

    public void setChantierPlannedEndDate(Date chantierPlannedEndDate) {
        this.set(PERIMETRE_CHANTIER_PLANNED_DATE, chantierPlannedEndDate);
    }

    public Date getChantierEndDate() {
        return this.get(PERIMETRE_CHANTIER_END_DATE);
    }

    public void setChantierEndDate(Date chantierEndDate) {
        this.set(PERIMETRE_CHANTIER_END_DATE, chantierEndDate);
    }

    public Integer getIsDeleted() {
        return this.get(PERIMETRE_IS_DELETED);
    }

    public void setIsDeleted(Integer isDeleted) {
        this.set(PERIMETRE_IS_DELETED, isDeleted);
    }

    public String getArgosName() {
        return this.get(PERIMETRE_ARGOS_NAME);
    }

    public void setArgosName(String argosName) {
        this.set(PERIMETRE_ARGOS_NAME, argosName);
    }

    public ChantierTypeModel getChantierType() {
        return this.get(PERIMETRE_CHANTIER_TYPE);
    }

    public void setChantierType(ChantierTypeModel chantierType) {
        this.set(PERIMETRE_CHANTIER_TYPE, chantierType);
    }

    public String getAddress() {
        return this.get(PERIMETRE_ADDRESS);
    }

    public void setAddress(String address) {
        this.set(PERIMETRE_ADDRESS, address);
    }

    public String getChantierNumeroDeProjet() {
        return this.get(PERIMETRE_CHANTIER_NUMERO_PROJECT);
    }

    public void setChantierNumeroDeProjet(String chantierNumeroDeProjet) {
        this.set(PERIMETRE_CHANTIER_NUMERO_PROJECT, chantierNumeroDeProjet);
    }

    public Integer getChantierGroupement() {
        return this.get(PERIMETRE_CHANTIER_GROUPEMENT);
    }

    public void setChantierGroupement(Integer chantierGroupement) {
        this.set(PERIMETRE_CHANTIER_GROUPEMENT, chantierGroupement);
    }

    public String getChantierPartenaires() {
        return this.get(PERIMETRE_CHANTIER_PARTENAIRES);
    }

    public void setChantierPartenaires(String chantierPartenaires) {
        this.set(PERIMETRE_CHANTIER_PARTENAIRES, chantierPartenaires);
    }

    public UserModel getCreatedBy() {
        return this.get(PERIMETRE_CREATED_BY);
    }

    public void setCreatedBy(UserModel createdBy) {
        this.set(PERIMETRE_CREATED_BY, createdBy);
    }

    public Integer getIsSubdelegable() {
        return this.get(PERIMETRE_IS_SUBDELEGABLE);
    }

    public void setIsSubdelegable(Integer isSubdelegable) {
        this.set(PERIMETRE_IS_SUBDELEGABLE, isSubdelegable);
    }

    public void removeUnusedOnList() {
        // setPerId(null);
        // setEntite(null);
        this.setEntiteJuridique(null);
        this.setParent(null);
        // setType(null);
        this.setLanguage(null);
        this.setCity(null);
        this.setChantierSEP(null);
        this.setChantierID(null);
        this.setChantierStartDate(null);
        this.setChantierPlannedEndDate(null);
        this.setChantierEndDate(null);
        this.setIsDeleted(null);
        this.setArgosName(null);
        // setChantierType(null);
        this.setAddress(null);
    }

}

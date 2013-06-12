package com.structis.vip.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DelegationModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DELEGATION_TYPE = "delegationType";
    public static final String DELEGATION_STATUS = "delegationStatus";
    public static final String DELEGATION_NATURE = "delegationNature";
    public static final String DELEGATION_PERIMETRE = "perimeter";
    public static final String DELEGATION_START_DATE = "startDate";
    public static final String DELEGATION_END_DATE = "endDate";
    public static final String DELEGATION_IS_SIGNED = "isSigned";
    public static final String DELEGATION_DELEGANT = "delegant";
    public static final String DELEGATION_DELEGANT_FIRST_NAME = "delegantFirstname";
    public static final String DELEGATION_DELEGANT_LAST_NAME = "delegantLastname";
    public static final String DELEGATION_DELEGATAIRE = "delegataire";
    public static final String DELEGATION_DELEGATAIRE_FIRST_NAME = "delegataireFirstname";
    public static final String DELEGATION_DELEGATAIRE_LAST_NAME = "delegataireLastname";
    public static final String DELEGATION_DELEGATAIRE_STATUT = "delegataireStatut";
    public static final String DELEGATION_DELEGANT_STATUT = "delegantStatut";
    public static final String DELEGATION_DELEGATAIRE_ADDRESS = "delegataireAddress";
    public static final String DELEGATION_DEMANDEUR = "demandeur";

    public static final String DELEGATION_DEMANDEUR_FIRSTNAME = "demandeurFirstname";
    public static final String DELEGATION_DEMANDEUR_LASTNAME = "demandeurLastname";
    public static final String DELEGATION_CONJOINT = "delegationConjointe";
    public static final String DELEGATION_DATE1 = "date1";
    public static final String DELEGATION_PLACE1 = "place1";
    public static final String DELEGATION_DATE2 = "date2";
    public static final String DELEGATION_PLACE2 = "place2";
    public static final String DELEGATION_DATE3 = "date3";
    public static final String DELEGATION_PLACE3 = "place3";

    public static final String DELEGATION_AMOUNT1 = "amount1";
    public static final String DELEGATION_AMOUNT2 = "amount2";
    public static final String DELEGATION_AMOUNT3 = "amount3";
    public static final String DELEGATION_AMOUNT4 = "amount4";
    public static final String DELEGATION_AMOUNT5 = "amount5";
    public static final String DELEGATION_COMMENT1 = "comment1";
    public static final String DELEGATION_COMMENT2 = "comment2";

    public static final String DELEGATION_ENTITE = "entite";
    public static final String DELEGATION_ENTITE_JURIDIQUE = "entiteJuridique";
    public static final String DELEGATION_ETJ_NAME = "etjName";
    public static final String DELEGATION_ETJ_STATUT = "etjStatut";
    public static final String DELEGATION_ETJ_CAPITAL = "etjCapital";
    public static final String DELEGATION_ETJ_REGISTRATION_ID = "etjRegistrationId";
    public static final String DELEGATION_ETJ_REGISTRATION_ADDRESS = "etjRegistrationAddress";
    public static final String DELEGATION_DELEGATAIRE_QUALITE = "delegataireQualite";
    public static final String DELEGATION_DELEGANT_QUALITE = "delegantQualite";
    public static final String DELEGATION_DELEGANT_TITLE = "delegantTitle";

    public static final String DELEGATION_PER_CHANTIER_NAME = "perChantierName";
    public static final String DELEGATION_PER_CHANTIER_CITY = "perChantierCity";
    public static final String DELEGATION_PER_CHANTIER_ID = "perChantierID";
    public static final String DELEGATION_PER_CHANTIER_DATE = "perChantierStartDate";
    public static final String DELEGATION_PER_CHANTIER_PLANED_ENDDATE = "perChantierPlannedEndDate";
    public static final String DELEGATION_PER_CHANTIER_ENDDATE = "perChantierEndDate";
    public static final String DELEGATION_PARENT = "parent";
    public static final String DELEGATION_PARTY_DELEGEE = "partyDelegee";

    public static final String DELEGATION_ETJ_ADDRESS = "etjAddress";
    public static final String DELEGATION_DESCRIPTION = "description";

    public static final String DELEGATION_DELEGANT_NIVEAU_HIERARCHIQUE = "delegantNiveauHierarchique";
    public static final String DELEGATION_DELEGANT_LIEU_NAISSANCE = "delegantLieuNaissance";
    public static final String DELEGATION_DELEGANT_NAISSANCE = "delegantDateNaissance";
    public static final String DELEGATION_DELEGANT_NATIONALITY = "delegantNationality";
    public static final String DELEGATION_DELEGATAIRE_NIVEAU_HIERARCHIQUE = "delegataireNiveauHierarchique";
    public static final String DELEGATION_DELEGATAIRE_LIEU_NAISSANCE = "delegataireLieuNaissance";
    public static final String DELEGATION_DELEGATAIRE_DATE_NAISSANCE = "delegataireDateNaissance";
    public static final String DELEGATION_DELEGATAIRE_NATIONALITY = "delegataireNationalite";

    public static final String DELEGATION_IS_MODIFICATION = "isModification";
    public static final String DELEGATION_IS_VALIDATION = "isValidation";
    public static final String DELEGATION_IS_LECTURE = "isLecture";
    public static final String DELEGATION_IS_CAN_RENEW = "isCanRenew";
    public static final String DELEGATION_OPERATIONS = "operations";
    public static final String DELEGATION_ZONE = "zone";

    private static final String DELEGATION_DELEGANT_STATUT_CONSEIL = "delegantStatutConseil";

    private static final String DELEGATION_DELEGANT_DATE_EFFET = "delegantDateEffet";

    private static final String DELEGATION_DELEGANT_DATE_CONSEIL = "delegantDateConseil";
    private static final String DELEGATION_DELEGANT_DATE_DELEGATION = "dateDelegation";

    private static final String DELEGATION_DELEGANT_NOM1 = "delegantNom1";

    private static final String DELEGATION_DELEGANT_PRENOM1 = "delegantPrenom1";

    private static final String DELEGATION_DELEGANT_QUALITE1 = "delegantQualite1";

    // public static final String DELEGATION_IS_SUBDELEGABLE = "isSubdelegable";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    @SuppressWarnings("unused")
    private EntiteJuridiqueModel entiteJuridiqueModel;

    @SuppressWarnings("unused")
    private DelegationTypeModel delegationTypeModel;

    @SuppressWarnings("unused")
    private DelegationNatureModel delegationNatureModel;

    @SuppressWarnings("unused")
    private DelegationStatusModel delegationStatusModel;

    @SuppressWarnings("unused")
    private PerimetreModel perimetreModel;

    @SuppressWarnings("unused")
    private CollaborateurModel collaborateurModel;

    private List<DelegationDelegataireModel> lstDelegataires = new ArrayList<DelegationDelegataireModel>();

    public DelegationTypeModel getDelegationType() {
        return this.get(DELEGATION_TYPE);
    }

    public void setDelegationType(DelegationTypeModel delegationType) {
        this.set(DELEGATION_TYPE, delegationType);
    }

    public DelegationStatusModel getDelegationStatus() {
        return this.get(DELEGATION_STATUS);
    }

    public void setDelegationStatus(DelegationStatusModel delegationStatus) {
        this.set(DELEGATION_STATUS, delegationStatus);
    }

    public DelegationNatureModel getDelegationNature() {
        return this.get(DELEGATION_NATURE);
    }

    public void setDelegationNature(DelegationNatureModel delegationNature) {
        this.set(DELEGATION_NATURE, delegationNature);
    }

    public PerimetreModel getPerimeter() {
        return this.get(DELEGATION_PERIMETRE);
    }

    public void setPerimeter(PerimetreModel perimeter) {
        this.set(DELEGATION_PERIMETRE, perimeter);
    }

    public Date getStartDate() {
        return this.get(DELEGATION_START_DATE);
    }

    public void setStartDate(Date startDate) {
        this.set(DELEGATION_START_DATE, startDate);
    }

    public Date getEndDate() {
        return this.get(DELEGATION_END_DATE);
    }

    public void setEndDate(Date endDate) {
        this.set(DELEGATION_END_DATE, endDate);
    }

    public Integer getIsSigned() {
        return this.get(DELEGATION_IS_SIGNED);
    }

    public void setIsSigned(Integer isSigned) {
        this.set(DELEGATION_IS_SIGNED, isSigned);
    }

    public CollaborateurModel getDelegant() {
        return this.get(DELEGATION_DELEGANT);
    }

    public void setDelegant(CollaborateurModel delegant) {
        this.set(DELEGATION_DELEGANT, delegant);
    }

    public String getDelegantFirstname() {
        return this.get(DELEGATION_DELEGANT_FIRST_NAME);
    }

    public void setDelegantFirstname(String delegantFirstname) {
        this.set(DELEGATION_DELEGANT_FIRST_NAME, delegantFirstname);
    }

    public String getDelegantLastname() {
        return this.get(DELEGATION_DELEGANT_LAST_NAME);
    }

    public void setDelegantLastname(String delegantLastname) {
        this.set(DELEGATION_DELEGANT_LAST_NAME, delegantLastname);
    }

    public CollaborateurModel getDelegataire() {
        return this.get(DELEGATION_DELEGATAIRE);
    }

    public void setDelegataire(CollaborateurModel delegataire) {
        this.set(DELEGATION_DELEGATAIRE, delegataire);
    }

    public String getDelegataireFirstname() {
        return this.get(DELEGATION_DELEGATAIRE_FIRST_NAME);
    }

    public void setDelegataireFirstname(String delegataireFirstname) {
        this.set(DELEGATION_DELEGATAIRE_FIRST_NAME, delegataireFirstname);
    }

    public String getDelegataireLastname() {
        return this.get(DELEGATION_DELEGATAIRE_LAST_NAME);
    }

    public void setDelegataireLastname(String delegataireLastname) {
        this.set(DELEGATION_DELEGATAIRE_LAST_NAME, delegataireLastname);
    }

    public CollaborateurModel getDemandeur() {
        return this.get(DELEGATION_DEMANDEUR);
    }

    public void setDemandeur(CollaborateurModel demandeur) {
        this.set(DELEGATION_DEMANDEUR, demandeur);
    }

    public String getDemandeurFirstname() {
        return this.get(DELEGATION_DEMANDEUR_FIRSTNAME);
    }

    public void setDemandeurFirstname(String demandeurFirstname) {
        this.set(DELEGATION_DEMANDEUR_FIRSTNAME, demandeurFirstname);
    }

    public String getDemandeurLastname() {
        return this.get(DELEGATION_DEMANDEUR_LASTNAME);
    }

    public void setDemandeurLastname(String demandeurLastname) {
        this.set(DELEGATION_DEMANDEUR_LASTNAME, demandeurLastname);
    }

    public Integer getDelegationConjointe() {
        return this.get(DELEGATION_CONJOINT);
    }

    public void setDelegationConjointe(Integer delegationConjoint) {
        this.set(DELEGATION_CONJOINT, delegationConjoint);
    }

    public Date getDate1() {
        return this.get(DELEGATION_DATE1);
    }

    public void setDate1(Date date1) {
        this.set(DELEGATION_DATE1, date1);
    }

    public String getPlace1() {
        return this.get(DELEGATION_PLACE1);
    }

    public void setPlace1(String place1) {
        this.set(DELEGATION_PLACE1, place1);
    }

    public Date getDate2() {
        return this.get(DELEGATION_DATE2);
    }

    public void setDate2(Date date2) {
        this.set(DELEGATION_DATE2, date2);
    }

    public String getPlace2() {
        return this.get(DELEGATION_PLACE2);
    }

    public void setPlace2(String place2) {
        this.set(DELEGATION_PLACE2, place2);
    }

    public Date getDate3() {
        return this.get(DELEGATION_DATE3);
    }

    public void setDate3(Date date3) {
        this.set(DELEGATION_DATE3, date3);
    }

    public String getPlace3() {
        return this.get(DELEGATION_PLACE3);
    }

    public void setPlace3(String place3) {
        this.set(DELEGATION_PLACE3, place3);
    }

    public Float getAmount1() {
        return this.get(DELEGATION_AMOUNT1);
    }

    public void setAmount1(Float amount1) {
        this.set(DELEGATION_AMOUNT1, amount1);
    }

    public Float getAmount2() {
        return this.get(DELEGATION_AMOUNT2);
    }

    public void setAmount2(Float amount2) {
        this.set(DELEGATION_AMOUNT2, amount2);
    }

    public Float getAmount3() {
        return this.get(DELEGATION_AMOUNT3);
    }

    public void setAmount3(Float amount3) {
        this.set(DELEGATION_AMOUNT3, amount3);
    }

    public Float getAmount4() {
        return this.get(DELEGATION_AMOUNT4);
    }

    public void setAmount4(Float amount4) {
        this.set(DELEGATION_AMOUNT4, amount4);
    }

    public String getComment1() {
        return this.get(DELEGATION_COMMENT1);
    }

    public void setComment1(String comment1) {
        this.set(DELEGATION_COMMENT1, comment1);
    }

    public String getComment2() {
        return this.get(DELEGATION_COMMENT2);
    }

    public void setComment2(String comment2) {
        this.set(DELEGATION_COMMENT2, comment2);
    }

    public EntiteJuridiqueModel getEntiteJuridique() {
        return this.get(DELEGATION_ENTITE_JURIDIQUE);
    }

    public void setEntiteJuridique(EntiteJuridiqueModel entiteJuridique) {
        this.set(DELEGATION_ENTITE_JURIDIQUE, entiteJuridique);
    }

    public EntiteModel getEntite() {
        return this.get(DELEGATION_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(DELEGATION_ENTITE, entite);
    }

    public String getEtjName() {
        return this.get(DELEGATION_ETJ_NAME);
    }

    public void setEtjName(String etjName) {
        this.set(DELEGATION_ETJ_NAME, etjName);
    }

    public String getEtjCapital() {
        return this.get(DELEGATION_ETJ_CAPITAL);
    }

    public void setEtjCapital(String etjCapital) {
        this.set(DELEGATION_ETJ_CAPITAL, etjCapital);
    }

    public String getEtjRegistrationId() {
        return this.get(DELEGATION_ETJ_REGISTRATION_ID);
    }

    public void setEtjRegistrationId(String etjRegistrationId) {
        this.set(DELEGATION_ETJ_REGISTRATION_ID, etjRegistrationId);
    }

    public String getEtjRegistrationAddress() {
        return this.get(DELEGATION_ETJ_REGISTRATION_ADDRESS);
    }

    public void setEtjRegistrationAddress(String etjRegistrationAddress) {
        this.set(DELEGATION_ETJ_REGISTRATION_ADDRESS, etjRegistrationAddress);
    }

    public String getDelegataireQualite() {
        return this.get(DELEGATION_DELEGATAIRE_QUALITE);
    }

    public void setDelegataireQualite(String delegataireQualite) {
        this.set(DELEGATION_DELEGATAIRE_QUALITE, delegataireQualite);
    }

    public String getDelegantQualite() {
        return this.get(DELEGATION_DELEGANT_QUALITE);
    }

    public void setDelegantQualite(String delegantQualite) {
        this.set(DELEGATION_DELEGANT_QUALITE, delegantQualite);
    }

    public String getPerChantierName() {
        return this.get(DELEGATION_PER_CHANTIER_NAME);
    }

    public void setPerChantierName(String perChantierName) {
        this.set(DELEGATION_PER_CHANTIER_NAME, perChantierName);
    }

    public String getPerChantierID() {
        return this.get(DELEGATION_PER_CHANTIER_ID);
    }

    public void setPerChantierID(String perChantierID) {
        this.set(DELEGATION_PER_CHANTIER_ID, perChantierID);
    }

    public Date getPerChantierStartDate() {
        return this.get(DELEGATION_PER_CHANTIER_DATE);
    }

    public void setPerChantierStartDate(Date perChantierStartDate) {
        this.set(DELEGATION_PER_CHANTIER_DATE, perChantierStartDate);
    }

    public Date getPerChantierPlannedEndDate() {
        return this.get(DELEGATION_PER_CHANTIER_PLANED_ENDDATE);
    }

    public void setPerChantierPlannedEndDate(Date perChantierPlannedEndDate) {
        this.set(DELEGATION_PER_CHANTIER_PLANED_ENDDATE, perChantierPlannedEndDate);
    }

    public Date getPerChantierEndDate() {
        return this.get(DELEGATION_PER_CHANTIER_ENDDATE);
    }

    public void setPerChantierEndDate(Date perChantierEndDate) {
        this.set(DELEGATION_PER_CHANTIER_ENDDATE, perChantierEndDate);
    }

    public DelegationModel getParent() {
        return this.get(DELEGATION_PARENT);
    }

    public void setParent(DelegationModel parent) {
        this.set(DELEGATION_PARENT, parent);
    }

    public String getPartyDelegee() {
        return this.get(DELEGATION_PARTY_DELEGEE);
    }

    public void setPartyDelegee(String partyDelegee) {
        this.set(DELEGATION_PARTY_DELEGEE, partyDelegee);
    }

    public static String getDelegationAmount5() {
        return DELEGATION_AMOUNT5;
    }

    public Float getAmount5() {
        return this.get(DELEGATION_AMOUNT5);
    }

    public void setAmount5(Float amount5) {
        this.set(DELEGATION_AMOUNT5, amount5);
    }

    public String getEtjAddress() {
        return this.get(DELEGATION_ETJ_ADDRESS);
    }

    public void setEtjAddress(String etjAddress) {
        this.set(DELEGATION_ETJ_ADDRESS, etjAddress);
    }

    public String getDescription() {
        return this.get(DELEGATION_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DELEGATION_DESCRIPTION, description);
    }

    public String getEtjStatut() {
        return this.get(DELEGATION_ETJ_STATUT);
    }

    public void setEtjStatut(String etjStatut) {
        this.set(DELEGATION_ETJ_STATUT, etjStatut);
    }

    public String getDelegantNiveauHierarchique() {
        return this.get(DELEGATION_DELEGANT_NIVEAU_HIERARCHIQUE);
    }

    public void setDelegantNiveauHierarchique(String delegantNiveauHierarchique) {
        this.set(DELEGATION_DELEGANT_NIVEAU_HIERARCHIQUE, delegantNiveauHierarchique);
    }

    public String getDelegantLieuNaissance() {
        return this.get(DELEGATION_DELEGANT_LIEU_NAISSANCE);
    }

    public void setDelegantLieuNaissance(String delegantLieuNaissance) {
        this.set(DELEGATION_DELEGANT_LIEU_NAISSANCE, delegantLieuNaissance);
    }

    public Date getDelegantDateNaissance() {
        return this.get(DELEGATION_DELEGANT_NAISSANCE);
    }

    public void setDelegantDateNaissance(Date delegantDateNaissance) {
        this.set(DELEGATION_DELEGANT_NAISSANCE, delegantDateNaissance);
    }

    public String getDelegantNationality() {
        return this.get(DELEGATION_DELEGANT_NATIONALITY);
    }

    public void setDelegantNationality(String delegantNationality) {
        this.set(DELEGATION_DELEGANT_NATIONALITY, delegantNationality);
    }

    public String getDelegataireLieuNaissance() {
        return this.get(DELEGATION_DELEGATAIRE_LIEU_NAISSANCE);
    }

    public void setDelegataireLieuNaissance(String delegataireLieuNaissance) {
        this.set(DELEGATION_DELEGATAIRE_LIEU_NAISSANCE, delegataireLieuNaissance);
    }

    public Date getDelegataireDateNaissance() {
        return this.get(DELEGATION_DELEGATAIRE_DATE_NAISSANCE);
    }

    public void setDelegataireDateNaissance(Date delegataireDateNaissance) {
        this.set(DELEGATION_DELEGATAIRE_DATE_NAISSANCE, delegataireDateNaissance);
    }

    public String getDelegataireNiveauHierarchique() {
        return this.get(DELEGATION_DELEGATAIRE_NIVEAU_HIERARCHIQUE);
    }

    public void setDelegataireNiveauHierarchique(String delegataireNiveauHierarchique) {
        this.set(DELEGATION_DELEGATAIRE_NIVEAU_HIERARCHIQUE, delegataireNiveauHierarchique);
    }

    public String getDelegataireNationalite() {
        return this.get(DELEGATION_DELEGATAIRE_NATIONALITY);
    }

    public void setDelegataireNationalite(String delegataireNationality) {
        this.set(DELEGATION_DELEGATAIRE_NATIONALITY, delegataireNationality);
    }

    public String getPerChantierCity() {
        return this.get(DELEGATION_PER_CHANTIER_CITY);
    }

    public void setPerChantierCity(String perChantierCity) {
        this.set(DELEGATION_PER_CHANTIER_CITY, perChantierCity);
    }

    public String getDelegantTitle() {
        return this.get(DELEGATION_DELEGANT_TITLE);
    }

    public void setDelegantTitle(String delegantTitle) {
        this.set(DELEGATION_DELEGANT_TITLE, delegantTitle);
    }

    public Boolean getIsModification() {
        return this.get(DELEGATION_IS_MODIFICATION);
    }

    public void setIsModification(Boolean isModification) {
        this.set(DELEGATION_IS_MODIFICATION, isModification);
    }

    public Boolean getIsValidation() {
        return this.get(DELEGATION_IS_VALIDATION);
    }

    public void setIsValidation(Boolean isValidation) {
        this.set(DELEGATION_IS_VALIDATION, isValidation);
    }

    public Boolean getIsLecture() {
        return this.get(DELEGATION_IS_LECTURE);
    }

    public void setIsLecture(Boolean isLecture) {
        this.set(DELEGATION_IS_LECTURE, isLecture);
    }

    public Boolean getIsCanRenew() {
        return this.get(DELEGATION_IS_CAN_RENEW);
    }

    public void setIsCanRenew(Boolean isCanRenew) {
        this.set(DELEGATION_IS_CAN_RENEW, isCanRenew);
    }

    public String getDelegataireStatut() {
        return this.get(DELEGATION_DELEGATAIRE_STATUT);
    }

    public void setDelegataireStatut(String delegataireStatut) {
        this.set(DELEGATION_DELEGATAIRE_STATUT, delegataireStatut);
    }

    public String getDelegataireAddress() {
        return this.get(DELEGATION_DELEGATAIRE_ADDRESS);
    }

    public void setDelegataireAddress(String delegataireAddress) {
        this.set(DELEGATION_DELEGATAIRE_ADDRESS, delegataireAddress);
    }

    public String getDelegantStatut() {
        return this.get(DELEGATION_DELEGANT_STATUT);
    }

    public void setDelegantStatut(String delegantStatut) {
        this.set(DELEGATION_DELEGANT_STATUT, delegantStatut);
    }

    public String getZone() {
        return this.get(DELEGATION_ZONE);
    }

    public void setZone(String zone) {
        this.set(DELEGATION_ZONE, zone);
    }

    public String getOperations() {
        return this.get(DELEGATION_OPERATIONS);
    }

    public void setOperations(String operations) {
        this.set(DELEGATION_OPERATIONS, operations);
    }

    // public Integer getIsSubdelegable() {
    // return get(DELEGATION_IS_SUBDELEGABLE);
    // }
    //
    // public void setIsSubdelegable(Integer isSubdelegable) {
    // set(DELEGATION_IS_SUBDELEGABLE, isSubdelegable);
    // }

    public List<DelegationDelegataireModel> getLstDelegataires() {
        return this.lstDelegataires;
    }

    public void setLstDelegataires(List<DelegationDelegataireModel> lstDelegataires) {
        this.lstDelegataires = lstDelegataires;
    }

    public void setDelegantStatutConseil(String delegantStatutConseil) {
        this.set(DELEGATION_DELEGANT_STATUT_CONSEIL, delegantStatutConseil);
    }

    public String getDelegantStatutConseil() {
        return this.get(DELEGATION_DELEGANT_STATUT_CONSEIL);
    }

    public void setDelegantDateEffet(Date delegantDateEffet) {
        this.set(DELEGATION_DELEGANT_DATE_EFFET, delegantDateEffet);
    }

    public Date getDelegantDateEffet() {
        return this.get(DELEGATION_DELEGANT_DATE_EFFET);
    }

    public void setDelegantDateConseil(Date delegantDateConseil) {
        this.set(DELEGATION_DELEGANT_DATE_CONSEIL, delegantDateConseil);
    }

    public Date getDelegantDateConseil() {
        return this.get(DELEGATION_DELEGANT_DATE_CONSEIL);
    }

    public void setDateDelegation(Date dateDelegation) {
        this.set(DELEGATION_DELEGANT_DATE_DELEGATION, dateDelegation);
    }

    public Date getDateDelegation() {
        return this.get(DELEGATION_DELEGANT_DATE_DELEGATION);
    }

    public void setDelegantNom1(String delegantNom1) {
        this.set(DELEGATION_DELEGANT_NOM1, delegantNom1);
    }

    public String getDelegantNom1() {
        return this.get(DELEGATION_DELEGANT_NOM1);
    }

    public void setDelegantPrenom1(String delegantPrenom1) {
        this.set(DELEGATION_DELEGANT_PRENOM1, delegantPrenom1);
    }

    public String getDelegantPrenom1() {
        return this.get(DELEGATION_DELEGANT_PRENOM1);
    }

    public void setDelegantQualite1(String delegantQualite1) {
        this.set(DELEGATION_DELEGANT_QUALITE1, delegantQualite1);
    }

    public String getDelegantQualite1() {
        return this.get(DELEGATION_DELEGANT_QUALITE1);
    }

    public void removeUnusedOnList() {
        // setId(null);
        // setDelegationStatus(null);
        // setDelegationType(null);
        this.getDelegationNature().removeUnusedOnList();
        this.getPerimeter().removeUnusedOnList();
        this.getDelegant().removeUnusedOnList();
        if (this.getDelegataire() != null)
            this.getDelegataire().removeUnusedOnList();
        // setStartDate(null);
        // setEndDate(null);
        this.setDelegantFirstname(null);
        this.setDelegantLastname(null);
        this.setDelegataireFirstname(null);
        this.setDelegataireLastname(null);
        this.setDemandeur(null);
        this.setDemandeurFirstname(null);
        this.setDemandeurLastname(null);
        this.setDelegationConjointe(null);
        this.setDate1(null);
        this.setPlace1(null);
        this.setDate2(null);
        this.setPlace2(null);
        this.setDate3(null);
        this.setPlace3(null);
        this.setAmount1(null);
        this.setAmount2(null);
        this.setAmount3(null);
        this.setAmount4(null);
        this.setComment1(null);
        this.setComment2(null);
        this.setEntiteJuridique(null);
        // setEntite(null);
        this.setEtjName(null);
        this.setEtjCapital(null);
        this.setEtjRegistrationId(null);
        this.setEtjRegistrationAddress(null);
        this.setDelegataireQualite(null);
        this.setDelegantQualite(null);
        this.setPerChantierName(null);
        this.setPerChantierID(null);
        this.setPerChantierStartDate(null);
        this.setPerChantierPlannedEndDate(null);
        this.setPerChantierEndDate(null);
        this.setParent(null);
        this.setPartyDelegee(null);
        this.setAmount5(null);
        this.setEtjAddress(null);
        this.setDescription(null);
        this.setEtjStatut(null);
        this.setDelegantNiveauHierarchique(null);
        this.setDelegantLieuNaissance(null);
        this.setDelegantDateNaissance(null);
        this.setDelegantNationality(null);
        this.setDelegataireLieuNaissance(null);
        this.setDelegataireDateNaissance(null);
        this.setDelegataireNiveauHierarchique(null);
        this.setDelegataireNationalite(null);
        this.setPerChantierCity(null);
        this.setDelegantTitle(null);
        this.setDelegataireStatut(null);
        this.setDelegataireAddress(null);
        this.setDelegantStatut(null);
    }
}

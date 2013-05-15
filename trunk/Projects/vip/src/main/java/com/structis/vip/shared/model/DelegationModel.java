package com.structis.vip.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;

public class DelegationModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DELEGATION_ID = "id";
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
	
//	public static final String DELEGATION_IS_SUBDELEGABLE = "isSubdelegable";	
	
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

	public Integer getId() {
		return get(DELEGATION_ID);
	}

	public void setId(Integer id) {
		set(DELEGATION_ID, id);
	}

	public DelegationTypeModel getDelegationType() {
		return get(DELEGATION_TYPE);
	}

	public void setDelegationType(DelegationTypeModel delegationType) {
		set(DELEGATION_TYPE, delegationType);
	}

	public DelegationStatusModel getDelegationStatus() {
		return get(DELEGATION_STATUS);
	}

	public void setDelegationStatus(DelegationStatusModel delegationStatus) {
		set(DELEGATION_STATUS, delegationStatus);
	}

	public DelegationNatureModel getDelegationNature() {
		return get(DELEGATION_NATURE);
	}

	public void setDelegationNature(DelegationNatureModel delegationNature) {
		set(DELEGATION_NATURE, delegationNature);
	}

	public PerimetreModel getPerimeter() {
		return get(DELEGATION_PERIMETRE);
	}

	public void setPerimeter(PerimetreModel perimeter) {
		set(DELEGATION_PERIMETRE, perimeter);
	}

	public Date getStartDate() {
		return get(DELEGATION_START_DATE);
	}

	public void setStartDate(Date startDate) {
		set(DELEGATION_START_DATE, startDate);
	}

	public Date getEndDate() {
		return get(DELEGATION_END_DATE);
	}

	public void setEndDate(Date endDate) {
		set(DELEGATION_END_DATE, endDate);
	}

	public Integer getIsSigned() {
		return get(DELEGATION_IS_SIGNED);
	}

	public void setIsSigned(Integer isSigned) {
		set(DELEGATION_IS_SIGNED, isSigned);
	}

	public CollaborateurModel getDelegant() {
		return get(DELEGATION_DELEGANT);
	}

	public void setDelegant(CollaborateurModel delegant) {
		set(DELEGATION_DELEGANT, delegant);
	}

	public String getDelegantFirstname() {
		return get(DELEGATION_DELEGANT_FIRST_NAME);
	}

	public void setDelegantFirstname(String delegantFirstname) {
		set(DELEGATION_DELEGANT_FIRST_NAME, delegantFirstname);
	}

	public String getDelegantLastname() {
		return get(DELEGATION_DELEGANT_LAST_NAME);
	}

	public void setDelegantLastname(String delegantLastname) {
		set(DELEGATION_DELEGANT_LAST_NAME, delegantLastname);
	}

	public CollaborateurModel getDelegataire() {
		return get(DELEGATION_DELEGATAIRE);
	}

	public void setDelegataire(CollaborateurModel delegataire) {
		set(DELEGATION_DELEGATAIRE, delegataire);
	}

	public String getDelegataireFirstname() {
		return get(DELEGATION_DELEGATAIRE_FIRST_NAME);
	}

	public void setDelegataireFirstname(String delegataireFirstname) {
		set(DELEGATION_DELEGATAIRE_FIRST_NAME, delegataireFirstname);
	}

	public String getDelegataireLastname() {
		return get(DELEGATION_DELEGATAIRE_LAST_NAME);
	}

	public void setDelegataireLastname(String delegataireLastname) {
		set(DELEGATION_DELEGATAIRE_LAST_NAME, delegataireLastname);
	}

	public CollaborateurModel getDemandeur() {
		return get(DELEGATION_DEMANDEUR);
	}

	public void setDemandeur(CollaborateurModel demandeur) {
		set(DELEGATION_DEMANDEUR, demandeur);
	}

	public String getDemandeurFirstname() {
		return get(DELEGATION_DEMANDEUR_FIRSTNAME);
	}

	public void setDemandeurFirstname(String demandeurFirstname) {
		set(DELEGATION_DEMANDEUR_FIRSTNAME, demandeurFirstname);
	}

	public String getDemandeurLastname() {
		return get(DELEGATION_DEMANDEUR_LASTNAME);
	}

	public void setDemandeurLastname(String demandeurLastname) {
		set(DELEGATION_DEMANDEUR_LASTNAME, demandeurLastname);
	}

	public Integer getDelegationConjointe() {
		return get(DELEGATION_CONJOINT);
	}

	public void setDelegationConjointe(Integer delegationConjoint) {
		set(DELEGATION_CONJOINT, delegationConjoint);
	}

	public Date getDate1() {
		return get(DELEGATION_DATE1);
	}

	public void setDate1(Date date1) {
		set(DELEGATION_DATE1, date1);
	}

	public String getPlace1() {
		return get(DELEGATION_PLACE1);
	}

	public void setPlace1(String place1) {
		set(DELEGATION_PLACE1, place1);
	}

	public Date getDate2() {
		return get(DELEGATION_DATE2);
	}

	public void setDate2(Date date2) {
		set(DELEGATION_DATE2, date2);
	}

	public String getPlace2() {
		return get(DELEGATION_PLACE2);
	}

	public void setPlace2(String place2) {
		set(DELEGATION_PLACE2, place2);
	}

	public Date getDate3() {
		return get(DELEGATION_DATE3);
	}

	public void setDate3(Date date3) {
		set(DELEGATION_DATE3, date3);
	}

	public String getPlace3() {
		return get(DELEGATION_PLACE3);
	}

	public void setPlace3(String place3) {
		set(DELEGATION_PLACE3, place3);
	}
	
	public Float getAmount1() {
		return get(DELEGATION_AMOUNT1);
	}

	public void setAmount1(Float amount1) {
		set(DELEGATION_AMOUNT1, amount1);
	}

	public Float getAmount2() {
		return get(DELEGATION_AMOUNT2);
	}

	public void setAmount2(Float amount2) {
		set(DELEGATION_AMOUNT2, amount2);
	}

	public Float getAmount3() {
		return get(DELEGATION_AMOUNT3);
	}

	public void setAmount3(Float amount3) {
		set(DELEGATION_AMOUNT3, amount3);
	}

	public Float getAmount4() {
		return get(DELEGATION_AMOUNT4);
	}

	public void setAmount4(Float amount4) {
		set(DELEGATION_AMOUNT4, amount4);
	}

	public String getComment1() {
		return get(DELEGATION_COMMENT1);
	}

	public void setComment1(String comment1) {
		set(DELEGATION_COMMENT1, comment1);
	}

	public String getComment2() {
		return get(DELEGATION_COMMENT2);
	}

	public void setComment2(String comment2) {
		set(DELEGATION_COMMENT2, comment2);
	}

	public EntiteJuridiqueModel getEntiteJuridique() {
		return get(DELEGATION_ENTITE_JURIDIQUE);
	}

	public void setEntiteJuridique(EntiteJuridiqueModel entiteJuridique) {
		set(DELEGATION_ENTITE_JURIDIQUE, entiteJuridique);
	}
	
	public EntiteModel getEntite() {
		return get(DELEGATION_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(DELEGATION_ENTITE, entite);
	}

	public String getEtjName() {
		return get(DELEGATION_ETJ_NAME);
	}

	public void setEtjName(String etjName) {
		set(DELEGATION_ETJ_NAME, etjName);
	}

	public String getEtjCapital() {
		return get(DELEGATION_ETJ_CAPITAL);
	}

	public void setEtjCapital(String etjCapital) {
		set(DELEGATION_ETJ_CAPITAL, etjCapital);
	}

	public String getEtjRegistrationId() {
		return get(DELEGATION_ETJ_REGISTRATION_ID);
	}

	public void setEtjRegistrationId(String etjRegistrationId) {
		set(DELEGATION_ETJ_REGISTRATION_ID, etjRegistrationId);
	}

	public String getEtjRegistrationAddress() {
		return get(DELEGATION_ETJ_REGISTRATION_ADDRESS);
	}

	public void setEtjRegistrationAddress(String etjRegistrationAddress) {
		set(DELEGATION_ETJ_REGISTRATION_ADDRESS, etjRegistrationAddress);
	}

	public String getDelegataireQualite() {
		return get(DELEGATION_DELEGATAIRE_QUALITE);
	}

	public void setDelegataireQualite(String delegataireQualite) {
		set(DELEGATION_DELEGATAIRE_QUALITE, delegataireQualite);
	}

	public String getDelegantQualite() {
		return get(DELEGATION_DELEGANT_QUALITE);
	}

	public void setDelegantQualite(String delegantQualite) {
		set(DELEGATION_DELEGANT_QUALITE, delegantQualite);
	}

	public String getPerChantierName() {
		return get(DELEGATION_PER_CHANTIER_NAME);
	}

	public void setPerChantierName(String perChantierName) {
		set(DELEGATION_PER_CHANTIER_NAME, perChantierName);
	}

	public String getPerChantierID() {
		return get(DELEGATION_PER_CHANTIER_ID);
	}

	public void setPerChantierID(String perChantierID) {
		set(DELEGATION_PER_CHANTIER_ID, perChantierID);
	}

	public Date getPerChantierStartDate() {
		return get(DELEGATION_PER_CHANTIER_DATE);
	}

	public void setPerChantierStartDate(Date perChantierStartDate) {
		set(DELEGATION_PER_CHANTIER_DATE, perChantierStartDate);
	}

	public Date getPerChantierPlannedEndDate() {
		return get(DELEGATION_PER_CHANTIER_PLANED_ENDDATE);
	}

	public void setPerChantierPlannedEndDate(Date perChantierPlannedEndDate) {
		set(DELEGATION_PER_CHANTIER_PLANED_ENDDATE, perChantierPlannedEndDate);
	}

	public Date getPerChantierEndDate() {
		return get(DELEGATION_PER_CHANTIER_ENDDATE);
	}

	public void setPerChantierEndDate(Date perChantierEndDate) {
		set(DELEGATION_PER_CHANTIER_ENDDATE, perChantierEndDate);
	}	
	
	public DelegationModel getParent() {
		return get(DELEGATION_PARENT);
	}

	public void setParent(DelegationModel parent) {
		set(DELEGATION_PARENT, parent);
	}

	public String getPartyDelegee() {
		return get(DELEGATION_PARTY_DELEGEE);
	}

	public void setPartyDelegee(String partyDelegee) {
		set(DELEGATION_PARTY_DELEGEE, partyDelegee);
	}

	public static String getDelegationAmount5() {
		return DELEGATION_AMOUNT5;
	}

	public Float getAmount5() {
		return get(DELEGATION_AMOUNT5);
	}

	public void setAmount5(Float amount5) {
		set(DELEGATION_AMOUNT5, amount5);
	}

	public String getEtjAddress() {
		return get(DELEGATION_ETJ_ADDRESS);
	}

	public void setEtjAddress(String etjAddress) {
		set(DELEGATION_ETJ_ADDRESS, etjAddress);
	}

	public String getDescription() {
		return get(DELEGATION_DESCRIPTION);
	}

	public void setDescription(String description) {
		set(DELEGATION_DESCRIPTION, description);
	}	
	
	public String getEtjStatut() {
		return get(DELEGATION_ETJ_STATUT);
	}

	public void setEtjStatut(String etjStatut) {
		set(DELEGATION_ETJ_STATUT, etjStatut);
	}	
	
	public String getDelegantNiveauHierarchique() {
		return get(DELEGATION_DELEGANT_NIVEAU_HIERARCHIQUE);
	}

	public void setDelegantNiveauHierarchique(String delegantNiveauHierarchique) {
		set(DELEGATION_DELEGANT_NIVEAU_HIERARCHIQUE, delegantNiveauHierarchique);
	}	
	
	public String getDelegantLieuNaissance() {
		return get(DELEGATION_DELEGANT_LIEU_NAISSANCE);
	}

	public void setDelegantLieuNaissance(String delegantLieuNaissance) {
		set(DELEGATION_DELEGANT_LIEU_NAISSANCE, delegantLieuNaissance);
	}	
	
	public Date getDelegantDateNaissance() {
		return get(DELEGATION_DELEGANT_NAISSANCE);
	}

	public void setDelegantDateNaissance(Date delegantDateNaissance) {
		set(DELEGATION_DELEGANT_NAISSANCE, delegantDateNaissance);
	}
	
	public String getDelegantNationality() {
		return get(DELEGATION_DELEGANT_NATIONALITY);
	}

	public void setDelegantNationality(String delegantNationality) {
		set(DELEGATION_DELEGANT_NATIONALITY, delegantNationality);
	}
	
	public String getDelegataireLieuNaissance() {
		return get(DELEGATION_DELEGATAIRE_LIEU_NAISSANCE);
	}

	public void setDelegataireLieuNaissance(String delegataireLieuNaissance) {
		set(DELEGATION_DELEGATAIRE_LIEU_NAISSANCE, delegataireLieuNaissance);
	}
	
	public Date getDelegataireDateNaissance() {
		return get(DELEGATION_DELEGATAIRE_DATE_NAISSANCE);
	}

	public void setDelegataireDateNaissance(Date delegataireDateNaissance) {
		set(DELEGATION_DELEGATAIRE_DATE_NAISSANCE, delegataireDateNaissance);
	}
	
	public String getDelegataireNiveauHierarchique() {
		return get(DELEGATION_DELEGATAIRE_NIVEAU_HIERARCHIQUE);
	}

	public void setDelegataireNiveauHierarchique(String delegataireNiveauHierarchique) {
		set(DELEGATION_DELEGATAIRE_NIVEAU_HIERARCHIQUE, delegataireNiveauHierarchique);
	}
	
	public String getDelegataireNationalite() {
		return get(DELEGATION_DELEGATAIRE_NATIONALITY);
	}

	public void setDelegataireNationalite(String delegataireNationality) {
		set(DELEGATION_DELEGATAIRE_NATIONALITY, delegataireNationality);
	}
	
	public String getPerChantierCity() {
		return get(DELEGATION_PER_CHANTIER_CITY);
	}

	public void setPerChantierCity(String perChantierCity) {
		set(DELEGATION_PER_CHANTIER_CITY, perChantierCity);
	}
	
	public String getDelegantTitle() {
		return get(DELEGATION_DELEGANT_TITLE);
	}

	public void setDelegantTitle(String delegantTitle) {
		set(DELEGATION_DELEGANT_TITLE, delegantTitle);
	}
	
	public Boolean getIsModification() {
		return get(DELEGATION_IS_MODIFICATION);
	}

	public void setIsModification(Boolean isModification) {
		set(DELEGATION_IS_MODIFICATION, isModification);
	}

	public Boolean getIsValidation() {
		return get(DELEGATION_IS_VALIDATION);
	}

	public void setIsValidation(Boolean isValidation) {
		set(DELEGATION_IS_VALIDATION, isValidation);
	}

	public Boolean getIsLecture() {
		return get(DELEGATION_IS_LECTURE);
	}

	public void setIsLecture(Boolean isLecture) {
		set(DELEGATION_IS_LECTURE, isLecture);
	}
	
	public Boolean getIsCanRenew() {
		return get(DELEGATION_IS_CAN_RENEW);
	}

	public void setIsCanRenew(Boolean isCanRenew) {
		set(DELEGATION_IS_CAN_RENEW, isCanRenew);
	}
	
	public String getDelegataireStatut() {
		return get(DELEGATION_DELEGATAIRE_STATUT);
	}

	public void setDelegataireStatut(String delegataireStatut) {
		set(DELEGATION_DELEGATAIRE_STATUT, delegataireStatut);
	}
	
	public String getDelegataireAddress() {
		return get(DELEGATION_DELEGATAIRE_ADDRESS);
	}

	public void setDelegataireAddress(String delegataireAddress) {
		set(DELEGATION_DELEGATAIRE_ADDRESS, delegataireAddress);
	}
	
	public String getDelegantStatut() {
		return get(DELEGATION_DELEGANT_STATUT);
	}

	public void setDelegantStatut(String delegantStatut) {
		set(DELEGATION_DELEGANT_STATUT, delegantStatut);
	}
	public String getZone() {
		return get(DELEGATION_ZONE);
	}

	public void setZone(String zone) {
		set(DELEGATION_ZONE, zone);
	}

	public String getOperations() {
		return get(DELEGATION_OPERATIONS);
	}

	public void setOperations(String operations) {
		set(DELEGATION_OPERATIONS, operations);
	}
//	public Integer getIsSubdelegable() {
//		return get(DELEGATION_IS_SUBDELEGABLE);
//	}
//
//	public void setIsSubdelegable(Integer isSubdelegable) {
//		set(DELEGATION_IS_SUBDELEGABLE, isSubdelegable);
//	}
	
	
	public List<DelegationDelegataireModel> getLstDelegataires() {
		return lstDelegataires;
	}

	public void setLstDelegataires(List<DelegationDelegataireModel> lstDelegataires) {
		this.lstDelegataires = lstDelegataires;
	}
	
	public void setDelegantStatutConseil(String delegantStatutConseil) {
		set(DELEGATION_DELEGANT_STATUT_CONSEIL, delegantStatutConseil);
	}

	public String getDelegantStatutConseil() {
		return get(DELEGATION_DELEGANT_STATUT_CONSEIL);
	}

	public void setDelegantDateEffet(Date delegantDateEffet) {
		set(DELEGATION_DELEGANT_DATE_EFFET, delegantDateEffet);
	}

	public Date getDelegantDateEffet() {
		return get(DELEGATION_DELEGANT_DATE_EFFET);
	}

	public void setDelegantDateConseil(Date delegantDateConseil) {
		set(DELEGATION_DELEGANT_DATE_CONSEIL, delegantDateConseil);
	}

	public Date getDelegantDateConseil() {
		return get(DELEGATION_DELEGANT_DATE_CONSEIL);
	}

	public void setDateDelegation(Date dateDelegation) {
		set(DELEGATION_DELEGANT_DATE_DELEGATION, dateDelegation);
	}

	public Date getDateDelegation() {
		return get(DELEGATION_DELEGANT_DATE_DELEGATION);
	}
	
	public void setDelegantNom1(String delegantNom1) {
		set(DELEGATION_DELEGANT_NOM1, delegantNom1);
	}
	
	public String getDelegantNom1() {
		return get(DELEGATION_DELEGANT_NOM1);
	}

	public void setDelegantPrenom1(String delegantPrenom1) {		
		set(DELEGATION_DELEGANT_PRENOM1, delegantPrenom1);
	}

	public String getDelegantPrenom1() {
		return get(DELEGATION_DELEGANT_PRENOM1);
	}

	public void setDelegantQualite1(String delegantQualite1) {
		set(DELEGATION_DELEGANT_QUALITE1, delegantQualite1);
	}

	public String getDelegantQualite1() {
		return get(DELEGATION_DELEGANT_QUALITE1);
	}
	
	public void removeUnusedOnList() {
		//setId(null);
		//setDelegationStatus(null);
		//setDelegationType(null);
		getDelegationNature().removeUnusedOnList();
		getPerimeter().removeUnusedOnList();
		getDelegant().removeUnusedOnList();
		if (getDelegataire() != null)
			getDelegataire().removeUnusedOnList();
		//setStartDate(null);
		//setEndDate(null);
		setDelegantFirstname(null);
		setDelegantLastname(null);
		setDelegataireFirstname(null);
		setDelegataireLastname(null);
		setDemandeur(null);
		setDemandeurFirstname(null);
		setDemandeurLastname(null);
		setDelegationConjointe(null);
		setDate1(null);
		setPlace1(null);
		setDate2(null);
		setPlace2(null);
		setDate3(null);
		setPlace3(null);
		setAmount1(null);
		setAmount2(null);
		setAmount3(null);
		setAmount4(null);
		setComment1(null);
		setComment2(null);
		setEntiteJuridique(null);
		//setEntite(null);
		setEtjName(null);
		setEtjCapital(null);
		setEtjRegistrationId(null);
		setEtjRegistrationAddress(null);
		setDelegataireQualite(null);
		setDelegantQualite(null);
		setPerChantierName(null);
		setPerChantierID(null);
		setPerChantierStartDate(null);
		setPerChantierPlannedEndDate(null);
		setPerChantierEndDate(null);
		setParent(null);
		setPartyDelegee(null);
		setAmount5(null);
		setEtjAddress(null);
		setDescription(null);
		setEtjStatut(null);
		setDelegantNiveauHierarchique(null);
		setDelegantLieuNaissance(null);
		setDelegantDateNaissance(null);
		setDelegantNationality(null);
		setDelegataireLieuNaissance(null);
		setDelegataireDateNaissance(null);
		setDelegataireNiveauHierarchique(null);
		setDelegataireNationalite(null);
		setPerChantierCity(null);
		setDelegantTitle(null);
		setDelegataireStatut(null);
		setDelegataireAddress(null);
		setDelegantStatut(null);
	}
}

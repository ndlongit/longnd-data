package com.structis.vip.shared.model;

import java.util.Date;
import java.util.List;

public class CollaborateurModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String COLLA_ID = "id";
	public static final String COLLA_CIVILITE = "civilite";
	public static final String COLLA_FIRST_NAME = "firstname";
	public static final String COLLA_LAST_NAME = "lastname";
	public static final String COLLA_NATIONALITY = "nationality";
	public static final String COLLA_DATE_ENTREE = "dateEntree";
	public static final String COLLA_DATE_NAISSANCE = "dateNaissance";
	public static final String COLLA_ID_BYCN = "idBycn";
	public static final String COLLA_IS_DELEGANT = "isDelegant";
	public static final String COLLA_IS_DELEGATAIRE = "isDelegataire";
	public static final String COLLA_ENTITE = "entite";
	public static final String COLLA_FULL_NAME = "fullname";
	public static final String COLLA_FULL_NAME_NO_SEPARATER = "fullnameNoSeparater";
	
	public static final String COLLA_FORMATIONS = "formations";
	
	public static final String COLLA_TYPE = "type";
	public static final String COLLA_QUALITE_DELEGANT = "qualiteDelegant";
	public static final String COLLA_DATE_CONSEIL = "dateConseil";
	public static final String COLLA_STATUT_CONSEIL = "statutConseil";
	public static final String COLLA_DATE_EFFET = "dateEffet";
	public static final String COLLA_DATE_DELEGATION = "dateDelegation";
	public static final String COLLA_DELEGANT = "delegant";
	public static final String COLLA_QUALITE_COL_DELEGANT = "qualiteColDelegant";
	
	public static final String COLLA_DATE_SORTIE = "dateSortie";
	public static final String COLLA_TITLE = "title";
	public static final String COLLA_ADDRESS = "address";
	public static final String COLLA_SORTI = "sorti";
	public static final String COLLA_DATE_MAJ_RUBIS = "dateMajRubis";
	public static final String COLLA_NIVEAU_HIERARCHIQUE = "niveauHierarchique";
	public static final String COLLA_LIEU_NAISSSANCE = "lieuNaissance";
	
	public static final String COLLA_PERIMETRE_DELEGANT = "perimetreDelegant";
	public static final String COLLA_PERIMETRE_DELEGATAIRE = "perimetreDelegataire";
	
	public static final String COLLA_OPERATIONS = "operations";
	public static final String COLLA_ZONE = "zone";
	
	public static final String COLLA_DELEGANT_PERIMETRES = "delegantPerimetreNames";
	public static final String COLLA_DELEGATAIRE_PERIMETRES = "delegatairesPerimetreNames";
			
	@SuppressWarnings("unused")
	private PerimetreModel perimetreDelegantModel;
	
	@SuppressWarnings("unused")
	private PerimetreModel perimetreDelegataireModel;
	
	@SuppressWarnings("unused")
	private CollaborateurTypeModel collaborateurTypeModel;
	
	@SuppressWarnings("unused")
	private CollaborateurModel delegantModel;
	
	@SuppressWarnings("unused")
	private EntiteModel entiteModel;
	
	@SuppressWarnings("unused")
	private List<FormationModel> formations;
	
	private List<DelegantPerimetreModel> delegantPerimetres;
	private List<DelegatairePerimetreModel> delegatairePerimetres;
	private int changeDelegantPerimetres = 0; // 0 : no change , 1: add , 2: replace
	private int changeDelegatairePerimetres = 0; // 0 : nochange , 1: add , 2: replace
	
	public Integer getId() {
		return get(COLLA_ID);
	}
	public void setId(Integer id) {
		set(COLLA_ID, id);
	}
	public String getCivilite() {
		return get(COLLA_CIVILITE);
	}
	public void setCivilite(String civilite) {
		set(COLLA_CIVILITE, civilite);
	}
	public String getFirstname() {
		return get(COLLA_FIRST_NAME);
	}
	public void setFirstname(String firstname) {
		set(COLLA_FIRST_NAME, firstname);
	}
	public String getLastname() {
		return get(COLLA_LAST_NAME);
	}
	public void setLastname(String lastname) {
		set(COLLA_LAST_NAME, lastname);
	}
	public String getNationality() {
		return get(COLLA_NATIONALITY);
	}
	public void setNationality(String nationality) {
		set(COLLA_NATIONALITY, nationality);
	}
	public Date getDateEntree() {
		return get(COLLA_DATE_ENTREE);
	}
	public void setDateEntree(Date dateEntree) {
		set(COLLA_DATE_ENTREE, dateEntree);
	}
	public Date getDateNaissance() {
		return get(COLLA_DATE_NAISSANCE);
	}
	public void setDateNaissance(Date dateNaissance) {
		set(COLLA_DATE_NAISSANCE, dateNaissance);
	}
	public String getIdBycn() {
		return get(COLLA_ID_BYCN);
	}
	public void setIdBycn(String idBycn) {
		set(COLLA_ID_BYCN, idBycn);
	}
	public Integer getIsDelegant() {
		return get(COLLA_IS_DELEGANT);
	}
	public void setIsDelegant(Integer isDelegant) {
		set(COLLA_IS_DELEGANT, isDelegant);
	}
	public Integer getIsDelegataire() {
		return get(COLLA_IS_DELEGATAIRE);
	}
	public void setIsDelegataire(Integer isDelegataire) {
		set(COLLA_IS_DELEGATAIRE, isDelegataire);
	}
	public EntiteModel getEntite() {
		return get(COLLA_ENTITE);
	}
	public void setEntite(EntiteModel entite) {
		set(COLLA_ENTITE, entite);
	}	
	public String getFullname() {
		return get(COLLA_FULL_NAME);
	}	
	public void setFullname(String fullName) {
		set(COLLA_FULL_NAME, fullName);
	}	
	
	public void setFullname() {
		set(COLLA_FULL_NAME, getLastname() + ", " + getFirstname());
	}
	
	public String getFullnameNoSeparater() {
		return get(COLLA_FULL_NAME_NO_SEPARATER);
	}
	public void setFullnameNoSeparater(String fullNameNoSeparater) {
		set(COLLA_FULL_NAME_NO_SEPARATER, fullNameNoSeparater);
	}
	public Date getDateSortie() {
		return get(COLLA_DATE_SORTIE);
	}
	public void setDateSortie(Date dateSortie) {
		set(COLLA_DATE_SORTIE, dateSortie);
	}
	
	public String getTitle() {
		return get(COLLA_TITLE);
	}
	public void setTitle(String title) {
		set(COLLA_TITLE, title);
	}
	
	public String getNiveauHierarchique() {
		return get(COLLA_NIVEAU_HIERARCHIQUE);
	}
	
	public void setNiveauHierarchique(String niveauHierarchique) {
		set(COLLA_NIVEAU_HIERARCHIQUE, niveauHierarchique);
	}
	
	public String getLieuNaissance() {
		return get(COLLA_LIEU_NAISSSANCE);
	}
	
	public void setLieuNaissance(String lieuNaissance) {
		set(COLLA_LIEU_NAISSSANCE, lieuNaissance);
	}
	
	public List<FormationModel> getFormations() {
		return get(COLLA_FORMATIONS);
	}
	
	public void setFormations(List<FormationModel> formations) {
		set(COLLA_FORMATIONS, formations);
	}
	public PerimetreModel getPerimetreDelegant() {
		return get(COLLA_PERIMETRE_DELEGANT);
	}
	public void setPerimetreDelegant(PerimetreModel perIdDelegant) {
		set(COLLA_PERIMETRE_DELEGANT, perIdDelegant);
	}
	
	public PerimetreModel getPerimetreDelegataire() {
		return get(COLLA_PERIMETRE_DELEGATAIRE);
	}
	public void setPerimetreDelegataire(PerimetreModel perIdDelegataire) {
		set(COLLA_PERIMETRE_DELEGATAIRE, perIdDelegataire);
	}
	
	public CollaborateurTypeModel getType() {
		return get(COLLA_TYPE);
	}
	
	public void setType(CollaborateurTypeModel collaborateurTypeModel) {
		set(COLLA_TYPE, collaborateurTypeModel);
	}
	
	public String getQualiteDelegant() {
		return get(COLLA_QUALITE_DELEGANT);
	}
	
	public void setQualiteDelegant(String qualiteDelegant) {
		set(COLLA_QUALITE_DELEGANT, qualiteDelegant);
	}
	
	public Date getDateConseil() {
		return get(COLLA_DATE_CONSEIL);
	}
	
	public void setDateConseil(Date dateConseil) {
		set(COLLA_DATE_CONSEIL, dateConseil);
	}
	
	public String getStatutConseil() {
		return get(COLLA_STATUT_CONSEIL);
	}
	
	public void setStatutConseil(String statutConseil) {
		set(COLLA_STATUT_CONSEIL, statutConseil);
	}
	
	public Date getDateEffet() {
		return get(COLLA_DATE_EFFET);
	}
	
	public void setDateEffet(Date dateEffet) {
		set(COLLA_DATE_EFFET, dateEffet);
	}
	
	public Date getDateDelegation() {
		return get(COLLA_DATE_DELEGATION);
	}
	
	public void setDateDelegation(Date dateDelegation) {
		set(COLLA_DATE_DELEGATION, dateDelegation);
	}
	
	public CollaborateurModel getDelegant() {
		return get(COLLA_DELEGANT);
	}
	
	public void setDelegant(CollaborateurModel delegant) {
		set(COLLA_DELEGANT, delegant);
	}
	
	public String getQualiteColDelegant() {
		return get(COLLA_QUALITE_COL_DELEGANT);
	}
	
	public void setQualiteColDelegant(String qualiteColDelegant) {
		set(COLLA_QUALITE_COL_DELEGANT, qualiteColDelegant);
	}
	
	public String getAddress() {
		return get(COLLA_ADDRESS);
	}
	
	public void setAddress(String address) {
		set(COLLA_ADDRESS, address);
	}
	
	public Date getDateMajRubis() {
		return get(COLLA_DATE_MAJ_RUBIS);
	}
	
	public void setDateMajRubis(Date dateMajRubis) {
		set(COLLA_DATE_MAJ_RUBIS, dateMajRubis);
	}
	
	public String getSorti() {
		return get(COLLA_SORTI);
	}
	
	public void setSorti(String sorti) {
		set(COLLA_SORTI, sorti);
	}
	
	public String getZone() {
		return get(COLLA_ZONE);
	}

	public void setZone(String zone) {
		set(COLLA_ZONE, zone);
	}

	public String getOperations() {
		return get(COLLA_OPERATIONS);
	}

	public void setOperations(String operations) {
		set(COLLA_OPERATIONS, operations);
	}
	
	
	
	public String getDelegantPerimetreNames() {
		return get(COLLA_DELEGANT_PERIMETRES);
	}
	public void setDelegantPerimetreNames(String delegantPerimetres) {
		set(COLLA_DELEGANT_PERIMETRES, delegantPerimetres);
	}
	public String getDelegatairesPerimetreNames() {
		return get(COLLA_DELEGATAIRE_PERIMETRES);
	}
	public void setDelegatairesPerimetreNames(String delegatairesPerimetres) {
		set(COLLA_DELEGATAIRE_PERIMETRES, delegatairesPerimetres);
	}
	
	public List<DelegantPerimetreModel> getDelegantPerimetres() {
		return delegantPerimetres;
	}
	public void setDelegantPerimetres(
			List<DelegantPerimetreModel> delegantPerimetres) {
		this.delegantPerimetres = delegantPerimetres;
	}
	public List<DelegatairePerimetreModel> getDelegatairePerimetres() {
		return delegatairePerimetres;
	}
	public void setDelegatairePerimetres(
			List<DelegatairePerimetreModel> delegatairePerimetres) {
		this.delegatairePerimetres = delegatairePerimetres;
	}
	
	
	public int isChangeDelegantPerimetres() {
		return changeDelegantPerimetres;
	}
	public void setChangeDelegantPerimetres(int changeDelegantPerimetres) {
		this.changeDelegantPerimetres = changeDelegantPerimetres;
	}
	public int isChangeDelegatairePerimetres() {
		return changeDelegatairePerimetres;
	}
	public void setChangeDelegatairePerimetres(int changeDelegatairePerimetres) {
		this.changeDelegatairePerimetres = changeDelegatairePerimetres;
	}
	public void removeUnusedOnList() {
		//setId(null);
		setCivilite(null);
		setFirstname(null);
		setLastname(null);
		setNationality(null);
		setDateEntree(null);
		setDateNaissance(null);
		setIdBycn(null);
		setIsDelegant(null);
		setIsDelegataire(null);
		setEntite(null);
		setDateSortie(null);
		setTitle(null);
		setNiveauHierarchique(null);
		setLieuNaissance(null);
		setFormations(null);
		setPerimetreDelegant(null);
		setPerimetreDelegataire(null);
		setType(null);
		setQualiteDelegant(null);
		setDateConseil(null);
		setStatutConseil(null);
		setDateEffet(null);
		setDateDelegation(null);
		setDelegant(null);
		setQualiteColDelegant(null);
		setAddress(null);
		setDateMajRubis(null);
		setSorti(null);
	}
	
	@Override
	public String toString() {
		return getFullnameNoSeparater();
	}
}
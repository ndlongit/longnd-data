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

    @Override
    public Integer getId() {
        return this.get(COLLA_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(COLLA_ID, id);
    }

    public String getCivilite() {
        return this.get(COLLA_CIVILITE);
    }

    public void setCivilite(String civilite) {
        this.set(COLLA_CIVILITE, civilite);
    }

    public String getFirstname() {
        return this.get(COLLA_FIRST_NAME);
    }

    public void setFirstname(String firstname) {
        this.set(COLLA_FIRST_NAME, firstname);
    }

    public String getLastname() {
        return this.get(COLLA_LAST_NAME);
    }

    public void setLastname(String lastname) {
        this.set(COLLA_LAST_NAME, lastname);
    }

    public String getNationality() {
        return this.get(COLLA_NATIONALITY);
    }

    public void setNationality(String nationality) {
        this.set(COLLA_NATIONALITY, nationality);
    }

    public Date getDateEntree() {
        return this.get(COLLA_DATE_ENTREE);
    }

    public void setDateEntree(Date dateEntree) {
        this.set(COLLA_DATE_ENTREE, dateEntree);
    }

    public Date getDateNaissance() {
        return this.get(COLLA_DATE_NAISSANCE);
    }

    public void setDateNaissance(Date dateNaissance) {
        this.set(COLLA_DATE_NAISSANCE, dateNaissance);
    }

    public String getIdBycn() {
        return this.get(COLLA_ID_BYCN);
    }

    public void setIdBycn(String idBycn) {
        this.set(COLLA_ID_BYCN, idBycn);
    }

    public Integer getIsDelegant() {
        return this.get(COLLA_IS_DELEGANT);
    }

    public void setIsDelegant(Integer isDelegant) {
        this.set(COLLA_IS_DELEGANT, isDelegant);
    }

    public Integer getIsDelegataire() {
        return this.get(COLLA_IS_DELEGATAIRE);
    }

    public void setIsDelegataire(Integer isDelegataire) {
        this.set(COLLA_IS_DELEGATAIRE, isDelegataire);
    }

    public EntiteModel getEntite() {
        return this.get(COLLA_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(COLLA_ENTITE, entite);
    }

    public String getFullname() {
        return this.get(COLLA_FULL_NAME);
    }

    public void setFullname(String fullName) {
        this.set(COLLA_FULL_NAME, fullName);
    }

    public void setFullname() {
        this.set(COLLA_FULL_NAME, this.getLastname() + ", " + this.getFirstname());
    }

    public String getFullnameNoSeparater() {
        return this.get(COLLA_FULL_NAME_NO_SEPARATER);
    }

    public void setFullnameNoSeparater(String fullNameNoSeparater) {
        this.set(COLLA_FULL_NAME_NO_SEPARATER, fullNameNoSeparater);
    }

    public Date getDateSortie() {
        return this.get(COLLA_DATE_SORTIE);
    }

    public void setDateSortie(Date dateSortie) {
        this.set(COLLA_DATE_SORTIE, dateSortie);
    }

    public String getTitle() {
        return this.get(COLLA_TITLE);
    }

    public void setTitle(String title) {
        this.set(COLLA_TITLE, title);
    }

    public String getNiveauHierarchique() {
        return this.get(COLLA_NIVEAU_HIERARCHIQUE);
    }

    public void setNiveauHierarchique(String niveauHierarchique) {
        this.set(COLLA_NIVEAU_HIERARCHIQUE, niveauHierarchique);
    }

    public String getLieuNaissance() {
        return this.get(COLLA_LIEU_NAISSSANCE);
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.set(COLLA_LIEU_NAISSSANCE, lieuNaissance);
    }

    public List<FormationModel> getFormations() {
        return this.get(COLLA_FORMATIONS);
    }

    public void setFormations(List<FormationModel> formations) {
        this.set(COLLA_FORMATIONS, formations);
    }

    public PerimetreModel getPerimetreDelegant() {
        return this.get(COLLA_PERIMETRE_DELEGANT);
    }

    public void setPerimetreDelegant(PerimetreModel perIdDelegant) {
        this.set(COLLA_PERIMETRE_DELEGANT, perIdDelegant);
    }

    public PerimetreModel getPerimetreDelegataire() {
        return this.get(COLLA_PERIMETRE_DELEGATAIRE);
    }

    public void setPerimetreDelegataire(PerimetreModel perIdDelegataire) {
        this.set(COLLA_PERIMETRE_DELEGATAIRE, perIdDelegataire);
    }

    public CollaborateurTypeModel getType() {
        return this.get(COLLA_TYPE);
    }

    public void setType(CollaborateurTypeModel collaborateurTypeModel) {
        this.set(COLLA_TYPE, collaborateurTypeModel);
    }

    public String getQualiteDelegant() {
        return this.get(COLLA_QUALITE_DELEGANT);
    }

    public void setQualiteDelegant(String qualiteDelegant) {
        this.set(COLLA_QUALITE_DELEGANT, qualiteDelegant);
    }

    public Date getDateConseil() {
        return this.get(COLLA_DATE_CONSEIL);
    }

    public void setDateConseil(Date dateConseil) {
        this.set(COLLA_DATE_CONSEIL, dateConseil);
    }

    public String getStatutConseil() {
        return this.get(COLLA_STATUT_CONSEIL);
    }

    public void setStatutConseil(String statutConseil) {
        this.set(COLLA_STATUT_CONSEIL, statutConseil);
    }

    public Date getDateEffet() {
        return this.get(COLLA_DATE_EFFET);
    }

    public void setDateEffet(Date dateEffet) {
        this.set(COLLA_DATE_EFFET, dateEffet);
    }

    public Date getDateDelegation() {
        return this.get(COLLA_DATE_DELEGATION);
    }

    public void setDateDelegation(Date dateDelegation) {
        this.set(COLLA_DATE_DELEGATION, dateDelegation);
    }

    public CollaborateurModel getDelegant() {
        return this.get(COLLA_DELEGANT);
    }

    public void setDelegant(CollaborateurModel delegant) {
        this.set(COLLA_DELEGANT, delegant);
    }

    public String getQualiteColDelegant() {
        return this.get(COLLA_QUALITE_COL_DELEGANT);
    }

    public void setQualiteColDelegant(String qualiteColDelegant) {
        this.set(COLLA_QUALITE_COL_DELEGANT, qualiteColDelegant);
    }

    public String getAddress() {
        return this.get(COLLA_ADDRESS);
    }

    public void setAddress(String address) {
        this.set(COLLA_ADDRESS, address);
    }

    public Date getDateMajRubis() {
        return this.get(COLLA_DATE_MAJ_RUBIS);
    }

    public void setDateMajRubis(Date dateMajRubis) {
        this.set(COLLA_DATE_MAJ_RUBIS, dateMajRubis);
    }

    public String getSorti() {
        return this.get(COLLA_SORTI);
    }

    public void setSorti(String sorti) {
        this.set(COLLA_SORTI, sorti);
    }

    public String getZone() {
        return this.get(COLLA_ZONE);
    }

    public void setZone(String zone) {
        this.set(COLLA_ZONE, zone);
    }

    public String getOperations() {
        return this.get(COLLA_OPERATIONS);
    }

    public void setOperations(String operations) {
        this.set(COLLA_OPERATIONS, operations);
    }

    public String getDelegantPerimetreNames() {
        return this.get(COLLA_DELEGANT_PERIMETRES);
    }

    public void setDelegantPerimetreNames(String delegantPerimetres) {
        this.set(COLLA_DELEGANT_PERIMETRES, delegantPerimetres);
    }

    public String getDelegatairesPerimetreNames() {
        return this.get(COLLA_DELEGATAIRE_PERIMETRES);
    }

    public void setDelegatairesPerimetreNames(String delegatairesPerimetres) {
        this.set(COLLA_DELEGATAIRE_PERIMETRES, delegatairesPerimetres);
    }

    public List<DelegantPerimetreModel> getDelegantPerimetres() {
        return this.delegantPerimetres;
    }

    public void setDelegantPerimetres(List<DelegantPerimetreModel> delegantPerimetres) {
        this.delegantPerimetres = delegantPerimetres;
    }

    public List<DelegatairePerimetreModel> getDelegatairePerimetres() {
        return this.delegatairePerimetres;
    }

    public void setDelegatairePerimetres(List<DelegatairePerimetreModel> delegatairePerimetres) {
        this.delegatairePerimetres = delegatairePerimetres;
    }

    public int isChangeDelegantPerimetres() {
        return this.changeDelegantPerimetres;
    }

    public void setChangeDelegantPerimetres(int changeDelegantPerimetres) {
        this.changeDelegantPerimetres = changeDelegantPerimetres;
    }

    public int isChangeDelegatairePerimetres() {
        return this.changeDelegatairePerimetres;
    }

    public void setChangeDelegatairePerimetres(int changeDelegatairePerimetres) {
        this.changeDelegatairePerimetres = changeDelegatairePerimetres;
    }

    public void removeUnusedOnList() {
        // setId(null);
        this.setCivilite(null);
        this.setFirstname(null);
        this.setLastname(null);
        this.setNationality(null);
        this.setDateEntree(null);
        this.setDateNaissance(null);
        this.setIdBycn(null);
        this.setIsDelegant(null);
        this.setIsDelegataire(null);
        this.setEntite(null);
        this.setDateSortie(null);
        this.setTitle(null);
        this.setNiveauHierarchique(null);
        this.setLieuNaissance(null);
        this.setFormations(null);
        this.setPerimetreDelegant(null);
        this.setPerimetreDelegataire(null);
        this.setType(null);
        this.setQualiteDelegant(null);
        this.setDateConseil(null);
        this.setStatutConseil(null);
        this.setDateEffet(null);
        this.setDateDelegation(null);
        this.setDelegant(null);
        this.setQualiteColDelegant(null);
        this.setAddress(null);
        this.setDateMajRubis(null);
        this.setSorti(null);
    }

    @Override
    public String toString() {
        return this.getFullnameNoSeparater();
    }
}

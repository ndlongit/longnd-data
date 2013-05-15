package com.structis.vip.server.bean.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "COL_COLLABORATEUR")
public class Collaborateur extends AbstractShowAbleBean implements
		Identifiable<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "col_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "col_civilite")
	private String civilite;

	@Column(name = "col_firstname")
	private String firstname;

	@Column(name = "col_lastname")
	private String lastname;

	@Column(name = "col_nationality")
	private String nationality;

	@Column(name = "col_dateEntree")
	private Date dateEntree;

	@Column(name = "col_dateNaissance")
	private Date dateNaissance;

	@Column(name = "col_idBycn")
	private String idBycn;

	@Column(name = "col_isDelegant")
	private Integer isDelegant;

	@Column(name = "col_isDelegataire")
	private Integer isDelegataire;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = false)
	private Entite entite = new Entite();
	
	@Column(name = "col_dateSortie")
	private Date dateSortie;
	
	@Column(name = "col_title")
	private String title;
	
	@Column(name = "col_address")
	private String address;
	
	@Column(name = "col_dateMajRubis")
	private Date dateMajRubis;
	
	@Column(name = "col_sorti")
	private String sorti;
	
	@Column(name = "col_niveauHierarchique")
	private String niveauHierarchique;
	
	@Column(name = "col_lieuNaissance")
	private String lieuNaissance;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "per_delegant_id", nullable = true)
	private Perimetre perimetreDelegant;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "per_delegataire_id", nullable = true)
	private Perimetre perimetreDelegataire;
	
	@Transient
	private String fullname;
	
	@Transient
	private String fullnameNoSeparater;
	
	@Transient
	private List<Formation> formations;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "cot_id", nullable = true)
	private CollaborateurType type;
	
	@Column(name = "col_qualiteDelegant")
	private String qualiteDelegant;
	
	@Column(name = "col_dateConseil")
	private Date dateConseil;
	
	@Column(name = "col_statutConseil")
	private String statutConseil;
	
	@Column(name = "col_dateEffet")
	private Date dateEffet;
	
	@Column(name = "col_dateDelegation")
	private Date dateDelegation;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "col_delegantId", nullable = true)
	private Collaborateur delegant;
	
	@Column(name = "col_qualiteColDelegant")
	private String qualiteColDelegant;
	
	@Column(name = "col_zone")
	private String zone;
	
	@Column(name = "col_operations")
	private String operations;
	
	@Transient
	private String delegantPerimetreNames;
	@Transient
	private String delegatairesPerimetreNames;
	
	@Override
	public Integer getPrimaryKey() {
		return getId();
	}

	@Override
	public void setPrimaryKey(Integer id) {
		setId(id);
	}

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getIdBycn() {
		return idBycn;
	}

	public void setIdBycn(String idBycn) {
		this.idBycn = idBycn;
	}

	public Integer getIsDelegant() {
		return isDelegant;
	}

	public void setIsDelegant(Integer isDelegant) {
		this.isDelegant = isDelegant;
	}

	public Integer getIsDelegataire() {
		return isDelegataire;
	}

	public void setIsDelegataire(Integer isDelegataire) {
		this.isDelegataire = isDelegataire;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public String getFullname() {
		this.fullname = this.lastname + ", " + this.firstname;
		return this.fullname;
	}

	public void setFullname(String fullName) {
		this.fullname = this.lastname + ", " + this.firstname;
	}

	public String getFullnameNoSeparater() {
		this.fullnameNoSeparater = this.lastname + " " + this.firstname;
		return fullnameNoSeparater;
	}

	public void setFullnameNoSeparater(String fullnameNoSeparater) {
		this.fullnameNoSeparater = this.lastname + " " + this.firstname;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNiveauHierarchique() {
		return niveauHierarchique;
	}

	public void setNiveauHierarchique(String niveauHierarchique) {
		this.niveauHierarchique = niveauHierarchique;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public Perimetre getPerimetreDelegant() {
		return perimetreDelegant;
	}

	public void setPerimetreDelegant(Perimetre perimetreDelegant) {
		this.perimetreDelegant = perimetreDelegant;
	}

	public Perimetre getPerimetreDelegataire() {
		return perimetreDelegataire;
	}

	public void setPerimetreDelegataire(Perimetre perimetreDelegataire) {
		this.perimetreDelegataire = perimetreDelegataire;
	}

	public CollaborateurType getType() {
		return type;
	}

	public void setType(CollaborateurType type) {
		this.type = type;
	}

	public String getQualiteDelegant() {
		return qualiteDelegant;
	}

	public void setQualiteDelegant(String qualiteDelegant) {
		this.qualiteDelegant = qualiteDelegant;
	}

	public Date getDateConseil() {
		return dateConseil;
	}

	public void setDateConseil(Date dateConseil) {
		this.dateConseil = dateConseil;
	}

	public String getStatutConseil() {
		return statutConseil;
	}

	public void setStatutConseil(String statutConseil) {
		this.statutConseil = statutConseil;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public Date getDateDelegation() {
		return dateDelegation;
	}

	public void setDateDelegation(Date dateDelegation) {
		this.dateDelegation = dateDelegation;
	}

	public Collaborateur getDelegant() {
		return delegant;
	}

	public void setDelegant(Collaborateur delegant) {
		this.delegant = delegant;
	}

	public String getQualiteColDelegant() {
		return qualiteColDelegant;
	}

	public void setQualiteColDelegant(String qualiteColDelegant) {
		this.qualiteColDelegant = qualiteColDelegant;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateMajRubis() {
		return dateMajRubis;
	}

	public void setDateMajRubis(Date dateMajRubis) {
		this.dateMajRubis = dateMajRubis;
	}

	public String getSorti() {
		return sorti;
	}

	public void setSorti(String sorti) {
		this.sorti = sorti;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getDelegantPerimetreNames() {
		return delegantPerimetreNames;
	}

	public void setDelegantPerimetreNames(String delegantPerimetreNames) {
		this.delegantPerimetreNames = delegantPerimetreNames;
	}

	public String getDelegatairesPerimetreNames() {
		return delegatairesPerimetreNames;
	}

	public void setDelegatairesPerimetreNames(String delegatairesPerimetreNames) {
		this.delegatairesPerimetreNames = delegatairesPerimetreNames;
	}

	
	public Collaborateur() {
		super();
	}

	public Collaborateur(Integer id, String firstname, String lastname,
			Integer isDelegant, Integer isDelegataire, String sorti,
			String delegantPerimetreNames, String delegatairesPerimetreNames) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isDelegant = isDelegant;
		this.isDelegataire = isDelegataire;
		this.sorti = sorti;
		this.delegantPerimetreNames = delegantPerimetreNames;
		this.delegatairesPerimetreNames = delegatairesPerimetreNames;
	}
	
	
}

package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;
import com.structis.fichesst.server.bean.domain.core.Timestampable;
import com.structis.fichesst.shared.dto.ChantierModel;

@Entity
@Table(name = "Chantier")
@AttributeOverride(name = "id", column = @Column(name = "id_chantier"))
public class Chantier extends NumericIdEntity implements Serializable,Timestampable {
	private Boolean barchive;
	private Date dateModif;
	private String idRefSiTravaux;
	private String nom;
	private Integer prorataTheorique;
	private List<Role> roles;
	private List<Conductor> conducteurs;
	private List<FicheTransfertpp> ficheTransfertPps;
	private List<Lot> lots;
	private List<Societe> societes;
	public Chantier() {
	}
	@Column(name = "b_archive")
	public Boolean getBarchive() {
		return this.barchive;
	}
	public void setBarchive(Boolean barchive) {
		this.barchive = barchive;
	}
	@Column(name = "id_ref_si_travaux")
	public String getIdRefSiTravaux() {
		return this.idRefSiTravaux;
	}
	public void setIdRefSiTravaux(String idRefSiTravaux) {
		this.idRefSiTravaux = idRefSiTravaux;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@OneToMany(mappedBy = "chantier",/*,cascade=CascadeType.ALL,*/ fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_chantier")
//	@OnDelete(action=OnDeleteAction.CASCADE)
	public List<Role> getRoles() {
		return this.roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@OneToMany(mappedBy="chantier",cascade=CascadeType.ALL,orphanRemoval = true,fetch=FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	public List<Conductor> getConducteurs() {
		return this.conducteurs;
	}
	public void setConducteurs(List<Conductor> conducteurs) {
		this.conducteurs = conducteurs;
	}

	//bi-directional many-to-one association to Fiche_Transfert_PP
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="chantier",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<FicheTransfertpp> getFicheTransfertPps() {
		return this.ficheTransfertPps;
	}
	public void setFicheTransfertPps(List<FicheTransfertpp> ficheTransfertPps) {
		this.ficheTransfertPps = ficheTransfertPps;
	}
	//bi-directional many-to-one association to Lot
	@OneToMany(mappedBy="chantier",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Lot> getLots() {
		return this.lots;
	}
	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}
	//bi-directional many-to-one association to Societe
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="chantier",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Societe> getSocietes() {
		return this.societes;
	}
	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}
	/*public Chantier(ChantierModel chantier){
		this.setId(chantier.getId());
		this.setNom(chantier.getNom());
	}
	*/
	public void addUser(UtilisateurGrp user){
		/*Role association=new Role();
		association.setUtilisateurGrp(user);
		association.setChantier(this);
//		association.setId(new Rolepk(user.getId(), this.getId()));
		association.getId().setIdChantier(this.getId());
		association.getId().setIdUtilisateurGrp(this.getId());*/
	}

	@Column(name = "prorata_theorique")
	public Integer getProrataTheorique() {
		return this.prorataTheorique;
	}
	public void setProrataTheorique(Integer prorataTheorique) {
		this.prorataTheorique = prorataTheorique;
	}
	@Override
	public void setModifiedDate(Date date) {
		this.dateModif=date;
	}
	@Column(name = "date_modif")
	@Override
	public Date getModifiedDate() {
		return dateModif;
	}

	
	
}

package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

/**
 * The persistent class for the Lig_Transfert_PP database table.
 * 
 */
@Entity
@Table(name = "Lig_Transfert_PP")
@AttributeOverride(name = "id", column = @Column(name = "id_lig_transfert_pp"))
public class LigTransfertPP extends NumericIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String commentaires;
	private String deVers;
	private String lot1;
	private String lot2;
	private Double pu;
	private Integer quantite;
	private FicheTransfertpp ficheTransfertPp;
	private RefTypeBudjConf refTypeBudjConf;
	private Integer idChantier;
	private Integer idTransfertpp;
	private Integer idBudj;
	@Column(name="id_type_budg_conf")
	public Integer getIdBudj() {
		return idBudj;
	}

	public void setIdBudj(Integer idBudj) {
		this.idBudj = idBudj;
	}

	@Column(name="id_chantier")
	public Integer getIdChantier() {
		return idChantier;
	}
	
	public void setIdChantier(Integer idChantier) {
		this.idChantier = idChantier;
	}
	@Column(name="id_transfert_pp")
	public Integer getIdTransfertpp() {
		return idTransfertpp;
	}

	public void setIdTransfertpp(Integer idTransfertpp) {
		this.idTransfertpp = idTransfertpp;
	}


	public LigTransfertPP() {
	}

	@Column(name = "commentaires")
	public String getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	@Column(name = "de_vers")
	public String getDeVers() {
		return this.deVers;
	}

	public void setDeVers(String deVers) {
		this.deVers = deVers;
	}

	@Column(name = "lot1")
	public String getLot1() {
		return this.lot1;
	}

	public void setLot1(String lot1) {
		this.lot1 = lot1;
	}

	@Column(name = "lot2")
	public String getLot2() {
		return this.lot2;
	}

	public void setLot2(String lot2) {
		this.lot2 = lot2;
	}

	@Column(name = "pu")
	public Double getPu() {
		return this.pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
	}

	@Column(name = "quantite")
	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	// bi-directional many-to-one association to Fiche_Transfert_PP
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "id_chantier", referencedColumnName = "id_chantier",insertable=false,updatable=false),
			@JoinColumn(name = "id_transfert_pp", referencedColumnName = "id_transfert_pp",insertable=false,updatable=false) })
	public FicheTransfertpp getFicheTransfertPp() {
		return this.ficheTransfertPp;
	}

	public void setFicheTransfertPp(FicheTransfertpp ficheTransfertPp) {
		this.ficheTransfertPp = ficheTransfertPp;
	}

	// bi-directional many-to-one association to Ref_Type_Budj_Conf
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_budg_conf",insertable=false,updatable=false)
	public RefTypeBudjConf getRefTypeBudjConf() {
		return this.refTypeBudjConf;
	}

	public void setRefTypeBudjConf(RefTypeBudjConf refTypeBudjConf) {
		this.refTypeBudjConf = refTypeBudjConf;
	}

}
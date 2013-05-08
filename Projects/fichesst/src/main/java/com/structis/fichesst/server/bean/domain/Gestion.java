package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@Entity
@Table(name = "LIG_SUIV_GEST")
@AttributeOverride(name = "id", column = @Column(name = "ID_LIG_SUIV_GEST"))
public class Gestion extends NumericIdEntity {
	private static final long serialVersionUID = 1L;

	private String devis;

	private Status statut;

	private String label;

	private String comment;

	private Double amount;

	private RefTypeMchAv marche;

	private Double arrete;

	private Double nonArrete;

	private Double provision;

	private Double devisRefuse;

	private Double reelActivitive;

	private String label2;

	private Double amount2;

	private Double traite;

	private FicheSt ficheSt;

	private RefTypeBudjConf type;

	private Boolean lock;
	public Gestion() {
	}
	
	@Column(name = "lock")
	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}
	
	@Column(name = "devis")
	public String getDevis() {
		return devis;
	}

	public void setDevis(String devis) {
		this.devis = devis;
	}

	@ManyToOne
	@JoinColumn(name = "id_statut")
	public Status getStatut() {
		return statut;
	}

	public void setStatut(Status statut) {
		this.statut = statut;
	}

	@Column(name = "libelle")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "commentaires")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "montant")
	public Double getAmount() {
		if( amount == null ) {
			return 0.0;
		}
		return amount;
	}

	public void setAmount(Double amount1) {
		this.amount = amount1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_mch_av")
	public RefTypeMchAv getMarche() {
		return marche;
	}

	public void setMarche(RefTypeMchAv refTypeMchAv) {
		this.marche = refTypeMchAv;
	}

	@Column(name = "montant_arrete")
	public Double getArrete() {
		if( arrete == null ) {
			return 0.0;
		}
		return arrete;
	}

	public void setArrete(Double arrete) {
		this.arrete = arrete;
	}

	@Column(name = "montant_non_arrete")
	public Double getNonArrete() {
		if( nonArrete == null ) {
			return 0.0;
		}
		return nonArrete;
	}

	public void setNonArrete(Double nonArrete) {
		this.nonArrete = nonArrete;
	}

	@Column(name = "provision")
	public Double getProvision() {
		if( provision == null ) {
			return 0.0;
		}
		return provision;
	}

	public void setProvision(Double provision) {
		this.provision = provision;
	}

	@Column(name = "devis_refuse")
	public Double getDevisRefuse() {
		if( devisRefuse == null ) {
			return 0.0;
		}
		return devisRefuse;
	}

	public void setDevisRefuse(Double devisRefuse) {
		this.devisRefuse = devisRefuse;
	}

	@Column(name = "activite_reel")
	public Double getReelActivitive() {
		if( reelActivitive == null ) {
			return 0.0;
		}
		return reelActivitive;
	}

	public void setReelActivitive(Double activiteReel) {
		this.reelActivitive = activiteReel;
	}

	@Column(name = "libelle_budg_conf")
	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	@Column(name = "montant_budg_conf")
	public Double getAmount2() {
		if( amount2 == null ) {
			return 0.0;
		}
		return amount2;
	}

	public void setAmount2(Double montantBudgConf) {
		this.amount2 = montantBudgConf;
	}

	@Column(name = "montant_mch_av")
	public Double getTraite() {
		if( traite == null ) {
			return 0.0;
		}
		return traite;
	}

	public void setTraite(Double traite) {
		this.traite = traite;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FICHE_ST")
	public FicheSt getFicheSt() {
		return ficheSt;
	}

	public void setFicheSt(FicheSt ficheSt) {
		this.ficheSt = ficheSt;
	}

	@ManyToOne
	@JoinColumn(name = "ID_TYPE_BUDG_CONF")
	public RefTypeBudjConf getType() {
		return type;
	}

	public void setType(RefTypeBudjConf type) {
		this.type = type;
	}
}

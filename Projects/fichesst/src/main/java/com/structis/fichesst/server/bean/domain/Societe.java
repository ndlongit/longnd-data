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
@Table(name = "Societe")
@AttributeOverride(name = "id", column = @Column(name = "id_societe"))
public class Societe extends NumericIdEntity {

	//Properties names
	public static final String PROP_NAME = "nom";

	private String idRefSiTravaux;

	private String nom;

	private Chantier chantier;

	public Societe() {
		setIdRefSiTravaux(DEFAULT_ID_REF_SITRAVAUX);
	}

	@Column(name = "id_ref_si_travaux")
	public String getIdRefSiTravaux() {
		return this.idRefSiTravaux;
	}

	public void setIdRefSiTravaux(String idRefSiTravaux) {
		this.idRefSiTravaux = idRefSiTravaux;
	}

	@Column(name = "nom")
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	//bi-directional many-to-one association to Chantier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chantier")
	public Chantier getChantier() {
		return this.chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}
}
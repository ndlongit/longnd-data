package com.structis.fichesst.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.structis.fichesst.server.bean.domain.core.CompositeKeyEntity;

@Entity
@Table(name = "Role")
public class Role extends CompositeKeyEntity<Rolepk> {
	private static final long serialVersionUID = 1L;
	private Boolean bcontributeur;
	private Boolean blecteur;
	private Chantier chantier;
	private UtilisateurGrp utilisateurGrp;
	public Role() {
	}

	@Column(name = "b_contributeur")
	public Boolean getBcontributeur() {
		return this.bcontributeur;

	}
	public void setBcontributeur(Boolean bcontributeur) {
		this.bcontributeur = bcontributeur;
	}
	@Column(name = "b_lecteur")
	public Boolean getBlecteur() {
		return this.blecteur;
	}
	public void setBlecteur(Boolean blecteur) {
		this.blecteur = blecteur;
	}
	// bi-directional many-to-one association to Chantier
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chantier", insertable = false, updatable = false)
	public Chantier getChantier() {
		return this.chantier;
	}
	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}
	// Used for Dozer mapping
	@Transient
	public Rolepk getRolePk() {
		return getId();
	}

	public void setRolePk(Rolepk rolePk) {
		setId(rolePk);
	}
	// bi-directional many-to-one association to UtilisateurGrp
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utilisateur_grp", insertable = false, updatable = false)
	public UtilisateurGrp getUtilisateurGrp() {
		return this.utilisateurGrp;
	}

	public void setUtilisateurGrp(UtilisateurGrp utilisateurGrp) {
		this.utilisateurGrp = utilisateurGrp;
	}
	public Role(Rolepk id, Boolean bcontributeur, Boolean blecteur) {
		setId(id);
		this.bcontributeur = bcontributeur;
		this.blecteur = blecteur;
	}

}
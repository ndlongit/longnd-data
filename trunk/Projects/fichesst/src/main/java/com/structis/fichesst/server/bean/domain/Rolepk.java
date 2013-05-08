package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rolepk implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idUtilisateurGrp;
	private Integer idChantier;

	public Rolepk() {
	}

	public Rolepk(Integer idUser, Integer idChantier) {
		this.idUtilisateurGrp = idUser;
		this.idChantier = idChantier;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "id_utilisateur_grp", unique = true, nullable = false)
	
	public Integer getIdUtilisateurGrp() {
		return this.idUtilisateurGrp;
	}

	public void setIdUtilisateurGrp(Integer idUtilisateurGrp) {
		this.idUtilisateurGrp = idUtilisateurGrp;
	}
//	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "id_chantier", unique = true, nullable = false)
	public Integer getIdChantier() {
		return this.idChantier;
	}

	public void setIdChantier(Integer idChantier) {
		this.idChantier = idChantier;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Rolepk)) {
			return false;
		}
		Rolepk castOther = (Rolepk) other;
		return (this.idUtilisateurGrp == castOther.idUtilisateurGrp)
				&& (this.idChantier == castOther.idChantier);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUtilisateurGrp;
		hash = hash * prime + this.idChantier;

		return hash;
	}

//	private Chantier chantier;
//	private UtilisateurGrp utilisateurGrp;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_chantier", nullable = false, insertable = false, updatable = false)
//	public Chantier getChantier() {
//		return this.chantier;
//	}
//
//	public void setChantier(Chantier chantier) {
//		this.chantier = chantier;
//	}
//
//	// bi-directional many-to-one association to UtilisateurGrp
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_utilisateur_grp", nullable = false, insertable = false, updatable = false)
//	public UtilisateurGrp getUtilisateurGrp() {
//		return this.utilisateurGrp;
//	}
//
//	public void setUtilisateurGrp(UtilisateurGrp utilisateurGrp) {
//		this.utilisateurGrp = utilisateurGrp;
//	}
}

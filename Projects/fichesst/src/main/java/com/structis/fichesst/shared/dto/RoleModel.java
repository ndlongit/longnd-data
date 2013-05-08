package com.structis.fichesst.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

public class RoleModel extends BaseModel {

	private static String B_Contributeur = "bcontributeur";
	private static String B_Lecteur = "blecteur";
	private static String ID_Chantier = "idChantier";
	private static String IDUTILISATEURGRP = "idUtilisateurGrp";
	private static String IDENTIFIANT = "identifiant"; // nom utilisateur

	public void setIdentifiant(String identifiant) {
		this.set(IDENTIFIANT, identifiant);
	}

	public String getIdentIfiant() {
		return this.get(IDENTIFIANT);
	}

	public void setIdChantier(Integer id) {
		this.set(ID_Chantier, id);
	}

	public Integer getIdChantier() {
		return this.get(ID_Chantier);
	}

	public void setIdUtilisateurGrp(Integer id) {
		this.set(IDUTILISATEURGRP, id);
	}

	public Integer getIdUtilisateurGrp() {
		return this.get(IDUTILISATEURGRP);
	}

	public void setBcontributeur(Boolean bcontributeur) {
		this.set(B_Contributeur, bcontributeur);

	}

	public Boolean getBcontributeur() {
		return this.get(B_Contributeur);
	}

	public void setBlecteur(Boolean blecteur) {
		this.set(B_Lecteur, blecteur);

	}

	public Boolean getBlecteur() {
		return this.get(B_Lecteur);
	}

	public RoleModel() {

	}

	public RoleModel(Integer idChantier, Integer idUtilisateurGrp,
			String identifiant, Boolean bcontributeur, Boolean blecteur) {
		set(ID_Chantier, idChantier);
		set(IDUTILISATEURGRP, idUtilisateurGrp);
		set(IDENTIFIANT, identifiant);
		set(B_Contributeur, bcontributeur);
		set(B_Lecteur, blecteur);
	}
}

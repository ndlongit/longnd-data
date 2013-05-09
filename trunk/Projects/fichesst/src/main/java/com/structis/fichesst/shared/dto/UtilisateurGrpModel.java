package com.structis.fichesst.shared.dto;

import java.util.Comparator;

import com.extjs.gxt.ui.client.data.BaseModel;

public class UtilisateurGrpModel extends BaseModel implements Comparator<UtilisateurGrpModel> {
	public final static String ID = "id";
	public final static String IDENTIFIANT = "identifiant";// login name
	public final static String ISADMIN = "badmin";
	public final static String B_Contributeur = "bcontributeur";
	public final static String B_Lecteur = "blecteur";
	public final static String ID_Chantier = "idChantier";

	public void setIdChantier(Integer id) {
		this.set(ID_Chantier, id);
	}

	public Integer getIdChantier() {
		return this.get(ID_Chantier);
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

	public void setId(Integer id) {
		this.set(ID, id);
	}

	public Integer getId() {
		return this.get(ID);
	}

	public void setIdentifiant(String identifiant) {
		this.set(IDENTIFIANT, identifiant);
	}

	public String getIdentifiant() {
		return this.get(IDENTIFIANT);
	}

	public void setBadmin(Boolean badmin) {
		this.set(ISADMIN, badmin);
	}

	public Boolean getBadmin() {
		return this.get(ISADMIN);
	}

	public UtilisateurGrpModel() {

	}

	public UtilisateurGrpModel(Integer id, String identifiant, Boolean badmin) {
		set(ID, id);
		set(IDENTIFIANT, identifiant);
		set(ISADMIN, badmin);
	}

	@Override
	public int compare(UtilisateurGrpModel o1, UtilisateurGrpModel o2) {
		if (o1.getIdentifiant() != null && o2.getIdentifiant() != null) {
			return o1.getIdentifiant().toString().compareToIgnoreCase(o2.getIdentifiant().toString());
		}
		return 0;
	}

	public UtilisateurGrpModel(Integer idChantier, Integer idUtilisateurGrp, String identifiant, Boolean bcontributeur, Boolean blecteur) {
		set(ID_Chantier, idChantier);
		set(ID, idUtilisateurGrp);
		set(IDENTIFIANT, identifiant);
		set(B_Contributeur, bcontributeur);
		set(B_Lecteur, blecteur);
	}

	@Override
	public String toString() {
		Integer id = getId();
		if (id == null) {
			return null;
		} else {
			return id.toString() + ": " + getIdentifiant();
		}
	}
}

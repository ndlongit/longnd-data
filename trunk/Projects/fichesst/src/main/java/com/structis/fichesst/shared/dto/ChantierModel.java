package com.structis.fichesst.shared.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ChantierModel extends AbstractDto implements Comparator<ChantierModel>,Serializable {

	public static final String BARCHIVE = "barchive";

	public static final String DATEMODIF = "dateModif";

	public static final String IDREFSITRAVAUX = "idRefSiTravaux";

	public static final String ROLES = "roles";

	public static final String NOM = "nom";

	public static final String PRORATA_THEORIQUE = "prorataTheorique";
	
	List<RoleModel> roles;
	
	public ChantierModel(){}
	public List<RoleModel> getRoles() {
		return this.get(ROLES);
	}
	public void setRoles(List<RoleModel> roles) {
		this.set(ROLES, roles);
	}

	public void setBarchive(Boolean barchive) {
		this.set(BARCHIVE, barchive);
	}

	public Boolean getBarchive() {
		return this.get(BARCHIVE);
	}

	public void setDateModif(Date dateModif) {
		this.set(DATEMODIF, dateModif);
	}

	public Date getDateModif() {
		return this.get(DATEMODIF);
	}

	public void setIdrefSiTravaux(String idRefSiTravaux) {
		this.set(IDREFSITRAVAUX, idRefSiTravaux);
	}

	public String getNom() {
		return this.get(NOM);
	}

	public void setNom(String nom) {
		this.set(NOM, nom);
	}

	public Integer getProrataTheorique() {
		return get(PRORATA_THEORIQUE, 0);
	}

	public void setProrataTheorique(Integer prorataTheorique) {
		set(PRORATA_THEORIQUE, prorataTheorique);
	}

	@Override
	public int compare(ChantierModel o1, ChantierModel o2) {
		// TODO Auto-generated method stub
		if( o1.getNom() != null && o2.getNom() != null ) {
			return o1.getNom().toString().compareToIgnoreCase(o2.getNom().toString());
		}
		return 0;
	}

}

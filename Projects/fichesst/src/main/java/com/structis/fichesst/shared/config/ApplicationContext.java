package com.structis.fichesst.shared.config;

import java.io.Serializable;
import java.util.Map;

import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class ApplicationContext implements Serializable {
	private String droits;

	private UtilisateurGrpModel userModel;

	private RoleModel roleModel;

	private ChantierModel currentChantier;

	private Integer transfertppId;

	private Integer fichestId;

	private Map<Integer, String> mapPrestations;
	private String societeName;

	public String getSocieteName() {
		return societeName;
	}

	public void setSocieteName(String societeName) {
		this.societeName = societeName;
	}

	public Integer getFichestId() {
		return fichestId;
	}

	public void setFichestId(Integer fichestId) {
		this.fichestId = fichestId;
	}

	public Integer getTransfertppId() {
		return transfertppId;
	}

	public void setTransfertppId(Integer transfertppId) {
		this.transfertppId = transfertppId;
	}

	public ChantierModel getCurrentChantier() {
		return currentChantier;
	}

	public void setCurrentChantier(ChantierModel currentChantier) {
		this.currentChantier = currentChantier;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public ApplicationContext() {
	}

	public ApplicationContext(UtilisateurGrpModel admin) {
		this.userModel = admin;
	}

	public UtilisateurGrpModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UtilisateurGrpModel userModel) {
		this.userModel = userModel;
	}

	public String[] getDroits() {
		return droits != null ? droits.split(",") : null;
	}

	public void setMapPrestations(Map<Integer, String> mapPrestations) {
		this.mapPrestations = mapPrestations;
	}

	public Map<Integer, String> getMapPrestations() {
		return mapPrestations;
	}
}

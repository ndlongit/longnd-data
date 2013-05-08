package com.structis.fichesst.shared.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class ApplicationContext implements Serializable{
	private String droits;
	private UtilisateurGrpModel userModel;
	
	private RoleModel roleModel;
	private ChantierModel currentChantier;
	
	private Map<Integer,String> mapPrestations;
	
	private Map<Integer,String> mapConducteurdetravaux;
	
	private Map<Integer,String> mapSociete;
	
	public ChantierModel getCurrentChantier() {
		return currentChantier;
	}
	public void setCurrentChantier(ChantierModel currentChantier) {
		this.currentChantier = currentChantier;
	}
	private HashMap<String, ChantierModel> mapChantier;
	public HashMap<String, ChantierModel> getMapChantier() {
		return mapChantier;
	}
	public void setMapChantier(HashMap<String, ChantierModel> mapChantier) {
		this.mapChantier = mapChantier;
	}
	public RoleModel getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}
	private Date date;
	public ApplicationContext(){}
	public ApplicationContext(UtilisateurGrpModel admin){
		
		this.userModel=admin;
	
	}
	public UtilisateurGrpModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UtilisateurGrpModel userModel) {
		this.userModel = userModel;
	}
	public String[] getDroits(){
		return droits!=null? droits.split(",") : null;
	}
	public void setMapPrestations(Map<Integer,String> mapPrestations) {
		this.mapPrestations = mapPrestations;
	}
	public Map<Integer,String> getMapPrestations() {
		return mapPrestations;
	}
	public void setMapConducteurdetravaux(Map<Integer,String> mapConducteurdetravaux) {
		this.mapConducteurdetravaux = mapConducteurdetravaux;
	}
	public Map<Integer,String> getMapConducteurdetravaux() {
		return mapConducteurdetravaux;
	}
	public void setMapSociete(Map<Integer,String> mapSociete) {
		this.mapSociete = mapSociete;
	}
	public Map<Integer,String> getMapSociete() {
		return mapSociete;
	}
}

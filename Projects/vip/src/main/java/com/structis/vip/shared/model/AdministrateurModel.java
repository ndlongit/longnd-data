package com.structis.vip.shared.model;


public class AdministrateurModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	
	public static String ADM_NOM = "nom";
	
	public static String ADM_PRENOM = "prenom";
	
	public static String ADM_DOMAINLOGON = "domainLogon";
	
	public String getNom(){
		return get(ADM_NOM);
	}
	
	public void setNom(String nom){
		set(ADM_NOM, nom);
	}
	
	public String getPrenom(){
		return get(ADM_PRENOM);
	}
	
	public void setPrenom(String prenom){
		set(ADM_PRENOM, prenom);
	}
	
	public String getDomainLogon(){
		return get(ADM_DOMAINLOGON);
	}
	
	public void setDomainLogon(String domainLogin){
		set(ADM_DOMAINLOGON, domainLogin);
	}
	
	public String getLibelle(){
		return get(ADM_DOMAINLOGON);
	}

}

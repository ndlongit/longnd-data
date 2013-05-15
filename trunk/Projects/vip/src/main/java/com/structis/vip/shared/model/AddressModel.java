package com.structis.vip.shared.model;

public class AddressModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 6807380804987897136L;
	public static final String ADD_BUREAUDISTRIBUTEUR = "bureauDistributeur";
	public static final String ADD_CODEPOSTAL = "codePostal";
	public static final String ADD_COMPLEMENTADDRESS = "complementAdresse";
	public static final String ADD_IDBYCN = "idbycn";
	public static final String ADD_NUMERORUE = "numeroRue";
	public static final String ADD_PAYS = "pays";
	public static final String ADD_VILLE = "ville";	
	
	public String getBureauDistributeur() {
		return get(ADD_BUREAUDISTRIBUTEUR);
	}

	public void setBureauDistributeur(String bureauDistributeur) {
		set(ADD_BUREAUDISTRIBUTEUR, bureauDistributeur);
	}
	
	public String getCodePostal() {
		return get(ADD_CODEPOSTAL);
	}

	public void setCodePostal(String codePostal) {
		set(ADD_CODEPOSTAL, codePostal);
	}
	
	public String getComplementAdresse() {
		return get(ADD_COMPLEMENTADDRESS);
	}

	public void setComplementAdresse(String complementAdresse) {
		set(ADD_COMPLEMENTADDRESS, complementAdresse);
	}
	
	public String getIdbycn() {
		return get(ADD_IDBYCN);
	}

	public void setIdbycn(String idbycn) {
		set(ADD_IDBYCN, idbycn);
	}
	
	public String getNumeroRue() {
		return get(ADD_NUMERORUE);
	}

	public void setNumeroRue(String numeroRue) {
		set(ADD_NUMERORUE, numeroRue);
	}
	
	public String getPays() {
		return get(ADD_PAYS);
	}

	public void setPays(String pays) {
		set(ADD_PAYS, pays);
	}
	
	public String getVille() {
		return get(ADD_VILLE);
	}

	public void setVille(String ville) {
		set(ADD_VILLE, ville);
	}
}
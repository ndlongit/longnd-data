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
        return this.get(ADD_BUREAUDISTRIBUTEUR);
    }

    public void setBureauDistributeur(String bureauDistributeur) {
        this.set(ADD_BUREAUDISTRIBUTEUR, bureauDistributeur);
    }

    public String getCodePostal() {
        return this.get(ADD_CODEPOSTAL);
    }

    public void setCodePostal(String codePostal) {
        this.set(ADD_CODEPOSTAL, codePostal);
    }

    public String getComplementAdresse() {
        return this.get(ADD_COMPLEMENTADDRESS);
    }

    public void setComplementAdresse(String complementAdresse) {
        this.set(ADD_COMPLEMENTADDRESS, complementAdresse);
    }

    public String getIdbycn() {
        return this.get(ADD_IDBYCN);
    }

    public void setIdbycn(String idbycn) {
        this.set(ADD_IDBYCN, idbycn);
    }

    public String getNumeroRue() {
        return this.get(ADD_NUMERORUE);
    }

    public void setNumeroRue(String numeroRue) {
        this.set(ADD_NUMERORUE, numeroRue);
    }

    public String getPays() {
        return this.get(ADD_PAYS);
    }

    public void setPays(String pays) {
        this.set(ADD_PAYS, pays);
    }

    public String getVille() {
        return this.get(ADD_VILLE);
    }

    public void setVille(String ville) {
        this.set(ADD_VILLE, ville);
    }
}

package com.structis.vip.shared.model;

public class AdministrateurModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static String ADM_NOM = "nom";

    public static String ADM_PRENOM = "prenom";

    public static String ADM_DOMAINLOGON = "domainLogon";

    public String getNom() {
        return this.get(ADM_NOM);
    }

    public void setNom(String nom) {
        this.set(ADM_NOM, nom);
    }

    public String getPrenom() {
        return this.get(ADM_PRENOM);
    }

    public void setPrenom(String prenom) {
        this.set(ADM_PRENOM, prenom);
    }

    public String getDomainLogon() {
        return this.get(ADM_DOMAINLOGON);
    }

    public void setDomainLogon(String domainLogin) {
        this.set(ADM_DOMAINLOGON, domainLogin);
    }

    @Override
    public String getLibelle() {
        return this.get(ADM_DOMAINLOGON);
    }

}

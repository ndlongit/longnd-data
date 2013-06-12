package com.structis.vip.shared.model;

public class DelegatairePerimetreModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DTP_DELEGATAIRE = "delegataire";
    public static final String DTP_PERIMETRE = "perimetre";

    @SuppressWarnings("unused")
    private CollaborateurModel delegataireModel;

    @SuppressWarnings("unused")
    private PerimetreModel perimetreModel;

    public CollaborateurModel getDelegataire() {
        return this.get(DTP_DELEGATAIRE);
    }

    public void setDelegataire(CollaborateurModel delegataire) {
        this.set(DTP_DELEGATAIRE, delegataire);
    }

    public PerimetreModel getPerimetre() {
        return this.get(DTP_PERIMETRE);
    }

    public void setPerimetre(PerimetreModel perimetre) {
        this.set(DTP_PERIMETRE, perimetre);
    }
}

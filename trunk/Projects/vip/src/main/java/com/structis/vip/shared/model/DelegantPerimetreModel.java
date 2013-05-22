package com.structis.vip.shared.model;

public class DelegantPerimetreModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DTP_ID = "id";
    public static final String DTP_DELEGANT = "delegant";
    public static final String DTP_PERIMETRE = "perimetre";

    @SuppressWarnings("unused")
    private CollaborateurModel delegantModel;

    @SuppressWarnings("unused")
    private PerimetreModel perimetreModel;

    @Override
    public Integer getId() {
        return this.get(DTP_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(DTP_ID, id);
    }

    public CollaborateurModel getDelegant() {
        return this.get(DTP_DELEGANT);
    }

    public void setDelegant(CollaborateurModel delegant) {
        this.set(DTP_DELEGANT, delegant);
    }

    public PerimetreModel getPerimetre() {
        return this.get(DTP_PERIMETRE);
    }

    public void setPerimetre(PerimetreModel perimetre) {
        this.set(DTP_PERIMETRE, perimetre);
    }
}

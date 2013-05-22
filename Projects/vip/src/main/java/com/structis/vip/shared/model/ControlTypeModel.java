package com.structis.vip.shared.model;

public class ControlTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String CON_ID = "id";
    public static final String CON_LABEL = "label";
    public static final String CON_DESCRIPTION = "description";
    public static final String CON_ENTITE = "entite";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    @Override
    public Integer getId() {
        return this.get(CON_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(CON_ID, id);
    }

    public String getLabel() {
        return this.get(CON_LABEL);
    }

    public void setLabel(String label) {
        this.set(CON_LABEL, label);
    }

    public EntiteModel getEntite() {
        return this.get(CON_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(CON_ENTITE, entite);
    }

    public String getDescription() {
        return this.get(CON_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(CON_DESCRIPTION, description);
    }

    public void removeUnusedOnList() {
        this.setEntite(null);
        this.setLabel(null);
        this.setLibelle(null);

    }
}

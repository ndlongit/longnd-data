package com.structis.vip.shared.model;

public class PerimetreTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String PERIMETRE_TYPE_ID = "ptyId";
    public static final String PERIMETRE_TYPE_NAME = "name";
    public static final String PERIMETRE_TYPE_ENTITE = "entite";
    public static final String PERIMETRE_TYPE_IS_SUBDELEGABLE = "isSubdelegable";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    public String getPtyId() {
        return this.get(PERIMETRE_TYPE_ID);
    }

    public void setPtyId(String id) {
        this.set(PERIMETRE_TYPE_ID, id);
    }

    public String getName() {
        return this.get(PERIMETRE_TYPE_NAME);
    }

    public void setName(String name) {
        this.set(PERIMETRE_TYPE_NAME, name);
    }

    public EntiteModel getEntite() {
        return this.get(PERIMETRE_TYPE_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(PERIMETRE_TYPE_ENTITE, entite);
    }

    public Integer getIsSubdelegable() {
        return this.get(PERIMETRE_TYPE_IS_SUBDELEGABLE);
    }

    public void setIsSubdelegable(Integer isSubdelegable) {
        this.set(PERIMETRE_TYPE_IS_SUBDELEGABLE, isSubdelegable);
    }
}

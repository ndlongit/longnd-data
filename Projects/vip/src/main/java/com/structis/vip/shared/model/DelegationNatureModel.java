package com.structis.vip.shared.model;

public class DelegationNatureModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DELE_NATURE_ID = "id";
    public static final String DELE_NATURE_NAME = "name";
    public static final String DELE_NATURE_DESCRIPTION = "description";
    public static final String DELE_NATURE_ENTITE = "entite";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    @Override
    public Integer getId() {
        return this.get(DELE_NATURE_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(DELE_NATURE_ID, id);
    }

    public String getName() {
        return this.get(DELE_NATURE_NAME);
    }

    public void setName(String name) {
        this.set(DELE_NATURE_NAME, name);
    }

    public String getDescription() {
        return this.get(DELE_NATURE_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DELE_NATURE_DESCRIPTION, description);
    }

    public EntiteModel getEntite() {
        return this.get(DELE_NATURE_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(DELE_NATURE_ENTITE, entite);
    }

    public void removeUnusedOnList() {
        // this.setId(null);
        // this.setName(null);
        this.setDescription(null);
        this.setEntite(null);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

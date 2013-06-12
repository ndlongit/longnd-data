package com.structis.vip.shared.model;

import com.structis.vip.shared.model.core.SimpleModel;

public class DelegationNatureModel extends SimpleModel {

    private static final long serialVersionUID = 1L;

    public static final String DELE_NATURE_ENTITE = "entite";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    public EntiteModel getEntite() {
        return this.get(DELE_NATURE_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(DELE_NATURE_ENTITE, entite);
    }

    public void removeUnusedOnList() {
        this.setDescription(null);
        this.setEntite(null);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

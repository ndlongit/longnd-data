package com.structis.vip.shared.model;

import java.util.Date;

public class FormationModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String FOR_LABEL = "label";
    public static final String FOR_DESCRIPTION = "description";
    public static final String FOR_ENTITE = "entite";
    public static final String FOR_DATE = "date";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    public String getLabel() {
        return this.get(FOR_LABEL);
    }

    public void setLabel(String label) {
        this.set(FOR_LABEL, label);
    }

    public String getDescription() {
        return this.get(FOR_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(FOR_DESCRIPTION, description);
    }

    public EntiteModel getEntite() {
        return this.get(FOR_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(FOR_ENTITE, entite);
    }

    public Date getDate() {
        return this.get(FOR_DATE);
    }

    public void setDate(Date entite) {
        this.set(FOR_DATE, entite);
    }

}

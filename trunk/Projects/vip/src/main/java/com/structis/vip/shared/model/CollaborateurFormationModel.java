package com.structis.vip.shared.model;

import java.util.Date;

public class CollaborateurFormationModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String CFO_DATE = "date";
    public static final String CFO_FORMATION = "formation";
    public static final String CFO_COLLABORATURE = "collaborateur";

    @SuppressWarnings("unused")
    private FormationModel formation;

    @SuppressWarnings("unused")
    private CollaborateurModel collaborateur;

    public Date getDate() {
        return this.get(CFO_DATE);
    }

    public void setDate(Date date) {
        this.set(CFO_DATE, date);
    }

    public FormationModel getFormation() {
        return this.get(CFO_FORMATION);
    }

    public void setFormation(FormationModel formation) {
        this.set(CFO_FORMATION, formation);
    }

    public CollaborateurModel getCollaborateur() {
        return this.get(CFO_COLLABORATURE);
    }

    public void setCollaborateur(CollaborateurModel col) {
        this.set(CFO_COLLABORATURE, col);
    }
}

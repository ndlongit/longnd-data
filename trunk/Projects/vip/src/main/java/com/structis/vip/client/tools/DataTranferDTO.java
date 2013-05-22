package com.structis.vip.client.tools;

import com.extjs.gxt.ui.client.data.BaseModel;

public class DataTranferDTO extends BaseModel implements Comparable<DataTranferDTO> {

    private static final long serialVersionUID = 1L;

    public static final String DATATRANFERDTO_PERIMETRE = "perimetre";
    public static final String DATATRANFERDTO_NATURE = "nature";
    public static final String DATATRANFERDTO_DELEGANT = "delegant";
    public static final String DATATRANFERDTO_DEBUTDEVALIDITE = "debutdevalidite";
    public static final String DATATRANFERDTO_FINDEVALIDITE = "findevalidite";
    public static final String DATATRANFERDTO_DELEGSIGNEE = "delegsignee";
    public static final String DATATRANFERDTO_TYPE = "type";
    public static final String DATATRANFERDTO_ACTION = "action";

    public DataTranferDTO() {
    }

    public String getPerimetre() {
        return this.get(DATATRANFERDTO_PERIMETRE);
    }

    public void setPerimetre(String perimetre) {
        this.set(DATATRANFERDTO_PERIMETRE, perimetre);
    }

    public String getNature() {
        return this.get(DATATRANFERDTO_NATURE);
    }

    public void setNature(String nature) {
        this.set(DATATRANFERDTO_NATURE, nature);
    }

    public String getDelegant() {
        return this.get(DATATRANFERDTO_DELEGANT);
    }

    public void setDelegant(String delegant) {
        this.set(DATATRANFERDTO_DELEGANT, delegant);
    }

    public String getDebutdevalidite() {
        return this.get(DATATRANFERDTO_DEBUTDEVALIDITE);
    }

    public void setDebutdevalidite(String debutdevalidite) {
        this.set(DATATRANFERDTO_DEBUTDEVALIDITE, debutdevalidite);
    }

    public String getFindevalidite() {
        return this.get(DATATRANFERDTO_FINDEVALIDITE);
    }

    public void setFindevalidite(String findevalidite) {
        this.set(DATATRANFERDTO_FINDEVALIDITE, findevalidite);
    }

    public String getDelegsignee() {
        return this.get(DATATRANFERDTO_DELEGSIGNEE);
    }

    public void setDelegsignee(String delegsignee) {
        this.set(DATATRANFERDTO_DELEGSIGNEE, delegsignee);
    }

    public String getType() {
        return this.get(DATATRANFERDTO_TYPE);
    }

    public void setType(String type) {
        this.set(DATATRANFERDTO_TYPE, type);
    }

    public String getAction() {
        return this.get(DATATRANFERDTO_ACTION);
    }

    public void setAction(String action) {
        this.set(DATATRANFERDTO_ACTION, action);
    }

    @Override
    public int compareTo(DataTranferDTO o) {
        return 0;
    }

    /*
     * @Override public String toString() { StringBuilder builder = new StringBuilder(); builder.append("DataTranferDTO ["); if (perimetre != null) {
     * builder.append("perimetre="); builder.append(perimetre); builder.append(", "); } if (nature != null) { builder.append("nature=");
     * builder.append(nature); builder.append(", "); } if (delegant != null) { builder.append("delegant="); builder.append(delegant);
     * builder.append(", "); } if (debutdevalidite != null) { builder.append("debutdevalidite="); builder.append(debutdevalidite);
     * builder.append(", "); } if (findevalidite != null) { builder.append("findevalidite="); builder.append(findevalidite); builder.append(", "); }
     * if (delegsignee != null) { builder.append("delegsignee="); builder.append(delegsignee); builder.append(", "); } if (type != null) {
     * builder.append("type="); builder.append(type); builder.append(", "); } if (action != null) { builder.append("action="); builder.append(action);
     * builder.append(", "); } builder.append("urgent="); builder.append(urgent); builder.append("]"); return builder.toString(); }
     */

}

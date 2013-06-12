package com.structis.vip.shared.model;

import java.util.Date;

public class ChantierTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String CTY_LABEL = "label";
    public static final String CTY_ENDDATE = "endDate";
    public static final String CTY_IS_SUBDELEGABLE = "isSubdelegable";
    public static final String CTY_ENTITE = "entite";

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    public String getLabel() {
        return this.get(CTY_LABEL);
    }

    public void setLabel(String label) {
        this.set(CTY_LABEL, label);
    }

    public Date getEndDate() {
        return this.get(CTY_ENDDATE);
    }

    public void setEndDate(Date endDate) {
        this.set(CTY_ENDDATE, endDate);
    }

    public Integer getIsSubdelegable() {
        return this.get(CTY_IS_SUBDELEGABLE);
    }

    public void setIsSubdelegable(Integer isSubdelegable) {
        this.set(CTY_IS_SUBDELEGABLE, isSubdelegable);
    }

    public EntiteModel getEntite() {
        return this.get(CTY_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(CTY_ENTITE, entite);
    }
}

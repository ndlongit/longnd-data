package com.structis.vip.shared.model;

public class DelegationDelegataireModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DED_COL_ID = "colId";
    public static final String DED_DEL_ID = "delId";
    public static final String DED_COL_NAME = "colName";

    public Integer getDelId() {
        return this.get(DED_DEL_ID);
    }

    public void setDelId(Integer delId) {
        this.set(DED_DEL_ID, delId);
    }

    public Integer getColId() {
        return this.get(DED_COL_ID);
    }

    public void setColId(Integer colId) {
        this.set(DED_COL_ID, colId);
    }

    public String getColName() {
        return this.get(DED_COL_NAME);
    }

    public void setColName(String colName) {
        this.set(DED_COL_NAME, colName);
    }

}

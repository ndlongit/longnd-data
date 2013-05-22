package com.structis.vip.shared.model;

public class FieldRuleModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 6807380804987897136L;
    public static final String FIR_ID = "id";
    public static final String FIR_DEM_GROUP = "group";
    public static final String FIR_IS_DISPLAYED = "isDisplayed";
    public static final String FIE_IS_REQUIRED = "isRequired";
    public static final String FIE_FIELD = "field";

    @SuppressWarnings("unused")
    private FieFieldModel fieldModel;

    @Override
    public Integer getId() {
        return this.get(FIR_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(FIR_ID, id);
    }

    public Integer getGroup() {
        return this.get(FIR_DEM_GROUP);
    }

    public void setGroup(Integer group) {
        this.set(FIR_DEM_GROUP, group);
    }

    public Integer getIsDisplayed() {
        return this.get(FIR_IS_DISPLAYED);
    }

    public void setIsDisplayed(Integer isDisplayed) {
        this.set(FIR_IS_DISPLAYED, isDisplayed);
    }

    public Integer getIsRequired() {
        return this.get(FIE_IS_REQUIRED);
    }

    public void setIsRequired(Integer isRequired) {
        this.set(FIE_IS_REQUIRED, isRequired);
    }

    public FieFieldModel getField() {
        return this.get(FIE_FIELD);
    }

    public void setField(FieFieldModel field) {
        this.set(FIE_FIELD, field);
    }
}

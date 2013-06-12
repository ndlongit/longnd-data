package com.structis.vip.shared.model;

public class LanguageModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String LAG_CODE = "code";
    public static final String LAG_NAME = "name";
    public static final String LAG_IS_DEFAULT = "isDefault";

    public String getCode() {
        return this.get(LAG_CODE);
    }

    public void setCode(String code) {
        this.set(LAG_CODE, code);
    }

    public String getName() {
        return this.get(LAG_NAME);
    }

    public void setName(String name) {
        this.set(LAG_NAME, name);
    }

    public Integer getIsDefault() {
        return this.get(LAG_IS_DEFAULT);
    }

    public void setIsDefault(Integer isDefault) {
        this.set(LAG_IS_DEFAULT, isDefault);
    }
}

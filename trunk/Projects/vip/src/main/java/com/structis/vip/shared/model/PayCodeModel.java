package com.structis.vip.shared.model;

public class PayCodeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String PAC_CODE = "code";
    public static final String PAC_NAME = "name";

    public String getCode() {
        return this.get(PAC_CODE);
    }

    public void setCode(String code) {
        this.set(PAC_CODE, code);
    }

    public String getName() {
        return this.get(PAC_NAME);
    }

    public void setName(String name) {
        this.set(PAC_NAME, name);
    }
}
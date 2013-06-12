package com.structis.vip.shared.model;

public class DelegationTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;
    public static final String DELEGATION_TYPE_NAME = "name";
    public static final String DELEGATION_TYPE_DESCRIPTION = "description";

    public static final Integer PRINCIPLE_TYPE = 1;
    public static final Integer SUB_TYPE = 2;
    public static final Integer TEMPORARY_TYPE = 3;

    public String getName() {
        return this.get(DELEGATION_TYPE_NAME);
    }

    public void setName(String name) {
        this.set(DELEGATION_TYPE_NAME, name);
    }

    public String getDescription() {
        return this.get(DELEGATION_TYPE_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DELEGATION_TYPE_DESCRIPTION, description);
    }
}

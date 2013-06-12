package com.structis.vip.shared.model;

public class DelegationStatusModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;
    public static final String DELEGATION_STATUS_NAME = "name";
    public static final String DELEGATION_STATUS_DESCRIPTION = "description";

    public String getName() {
        return this.get(DELEGATION_STATUS_NAME);
    }

    public void setName(String name) {
        this.set(DELEGATION_STATUS_NAME, name);
    }

    public String getDescription() {
        return this.get(DELEGATION_STATUS_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DELEGATION_STATUS_DESCRIPTION, description);
    }
}

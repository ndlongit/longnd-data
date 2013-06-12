package com.structis.vip.shared.model;

public class DelegantTypeGroupModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;
    public static final String DELEGANT_TYPE_GROUP_GROUP = "group";
    public static final String DELEGANT_TYPE_GROUP_NAME = "name";

    public String getName() {
        return this.get(DELEGANT_TYPE_GROUP_NAME);
    }

    public void setName(String name) {
        this.set(DELEGANT_TYPE_GROUP_NAME, name);
    }

    public String getGroup() {
        return this.get(DELEGANT_TYPE_GROUP_GROUP);
    }

    public void setGroup(String code) {
        this.set(DELEGANT_TYPE_GROUP_GROUP, code);
    }
}

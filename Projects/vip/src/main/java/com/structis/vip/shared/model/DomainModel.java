package com.structis.vip.shared.model;

public class DomainModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DOMAIN_ID = "id";
    public static final String DOMAIN_DESCRIPTION = "description";
    public static final String DOMAIN_NAME = "name";

    @Override
    public Integer getId() {
        return this.get(DOMAIN_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(DOMAIN_ID, id);
    }

    public String getDescription() {
        return this.get(DOMAIN_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DOMAIN_DESCRIPTION, description);
    }

    public String getName() {
        return this.get(DOMAIN_NAME);
    }

    public void setName(String name) {
        this.set(DOMAIN_NAME, name);
    }
}

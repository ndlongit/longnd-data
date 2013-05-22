package com.structis.vip.shared.model;

public class CategoryModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String CAT_ID = "id";
    public static final String CAT_NAME = "name";

    @Override
    public Integer getId() {
        return this.get(CAT_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(CAT_ID, id);
    }

    public String getName() {
        return this.get(CAT_NAME);
    }

    public void setName(String name) {
        this.set(CAT_NAME, name);
    }

}

package com.structis.vip.shared.model.core;

import com.structis.vip.shared.model.BaseModelDataActivable;

public abstract class SimpleModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";

    public String getDescription() {
        return this.get(DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(DESCRIPTION, description);
    }

    public String getName() {
        return this.get(NAME);
    }

    public void setName(String name) {
        this.set(NAME, name);
    }
}

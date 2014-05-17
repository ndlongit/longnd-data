package org.java.demo.search.core;

import org.java.demo.model.core.BasicEntity;

@SuppressWarnings("rawtypes")
public abstract class AbstractSearch<MODEL extends BasicEntity> {

    private MODEL model;

    protected AbstractSearch(MODEL model) {
        this.model = model;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }
}

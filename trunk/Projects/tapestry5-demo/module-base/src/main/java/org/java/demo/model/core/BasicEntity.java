package org.java.demo.model.core;

import java.io.Serializable;

public interface BasicEntity<ID extends Serializable> extends Serializable {

    // Properties names
    public static final String PROP_ID = "id";

    public static final String DEFAULT_ID_REF_SITRAVAUX = "abc";

    public ID getId();

    public void setId(ID id);

    public boolean isTransient();

    public String getOrderByClause();
}

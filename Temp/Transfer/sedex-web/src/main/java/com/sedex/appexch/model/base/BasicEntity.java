package com.sedex.appexch.model.base;

import java.io.Serializable;

public interface BasicEntity<ID extends Serializable> extends Serializable {

    /* Properties names - Begin */
    public static final String PROP_ID = "id";

    /* Properties names - End */

    public ID getId();

    public void setId(ID id);

    public boolean isTransient();
}

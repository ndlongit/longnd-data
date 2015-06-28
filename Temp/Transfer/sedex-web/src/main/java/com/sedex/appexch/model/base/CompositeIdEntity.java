package com.sedex.appexch.model.base;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class CompositeIdEntity<ID extends Serializable> implements BasicEntity<ID> {

    private static final long serialVersionUID = 1L;

    private ID id;

    // @EmbeddedId
    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    @Transient
    @Override
    public boolean isTransient() {
        return getId() == null;
    }
}
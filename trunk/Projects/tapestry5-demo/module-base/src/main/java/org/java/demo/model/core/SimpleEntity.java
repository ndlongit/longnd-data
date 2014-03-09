package org.java.demo.model.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class SimpleEntity extends NumericIdEntity {

    public static final String PROP_VALUE = "value";
    public static final String PROP_NAME = "name";
    public static final String PROP_DESCRIPTION = "description";

    private String name;
    private String value;

    private String description;

    @Column(name = "value", nullable = false, unique = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "name", nullable = false, unique = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Transient
    public String getOrderByClause() {
        return " order by " + SimpleEntity.PROP_NAME;
    }

    @Override
    @Transient
    public String toString() {
        return this.getName();
    }
}

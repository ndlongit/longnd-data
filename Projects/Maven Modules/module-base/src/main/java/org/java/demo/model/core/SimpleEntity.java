package org.java.demo.model.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class SimpleEntity extends NumericIdEntity {

    public static final String PROP_NAME = "name";
    public static final String PROP_CODE = "code";
    public static final String PROP_DESCRIPTION = "description";

    private String name;
    private String code;
    private String description;

    @Column(name = "code", nullable = false, unique = true)
    @Size(min = 2, max = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 2, max = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Transient
    public String toString() {
        return this.getName();
    }
}

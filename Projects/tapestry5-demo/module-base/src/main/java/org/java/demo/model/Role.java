package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.java.demo.model.core.SimpleEntity;

@Entity
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "role_name", unique = true, nullable = false))
public class Role extends SimpleEntity {

    public static final String PROP_VALUE = "value";
    
    private String value;

    @Column(name = "role_value", nullable = false, unique = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

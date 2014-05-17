package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.java.demo.model.core.SimpleEntity;

@Entity
@AttributeOverride(name = SimpleEntity.PROP_CODE, column = @Column(name = "code", unique = true, nullable = false))
public class Department extends SimpleEntity {

    @Transient
    @Override
    public Long getId() {
        return super.getId();
    }

    @Transient
    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}

package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.java.demo.model.core.SimpleEntity;

@Entity
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "role_name", unique = true, nullable = false))
public class Role extends SimpleEntity {
}

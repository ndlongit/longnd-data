package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.java.demo.model.core.SimpleEntity;
@Entity
@AttributeOverride(name = SimpleEntity.PROP_CODE, column = @Column(name = "code", unique = true, nullable = false))
public class Department extends SimpleEntity {
}

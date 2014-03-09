package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.java.demo.model.core.SimpleEntity;

@Entity
@Table(name = "Groups")
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "group_name"))
public class Group extends SimpleEntity {
}

package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.java.demo.model.core.SimpleEntity;

@Entity
@Table(name = "Roles")
@AttributeOverrides({ @AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "role_name", unique = true, nullable = false)),
        @AttributeOverride(name = SimpleEntity.PROP_CODE, column = @Column(name = "role_value", unique = true, nullable = false)) })
public class Role extends SimpleEntity {
}

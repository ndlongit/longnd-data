package org.java.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.java.demo.model.base.SimpleEntity;

@Entity
@Table(name = "Rights")
public class Right extends SimpleEntity {
}

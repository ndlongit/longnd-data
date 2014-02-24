package org.java.demo.model;

import javax.persistence.Entity;

import org.java.demo.model.core.SimpleEntity;
@Entity
public class Department extends SimpleEntity {

    private Employee manager;

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}

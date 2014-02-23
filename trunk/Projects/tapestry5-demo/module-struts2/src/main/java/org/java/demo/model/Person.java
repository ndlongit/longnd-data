package org.java.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.java.demo.model.core.NumericIdEntity;

@Entity
public class Person extends NumericIdEntity {

    private String lastName;
    private String firstName;
    private Employee manager;

    @ManyToOne
//    @JoinColumn(name = "manager_id")
    @ForeignKey(name = "fk_manager_id")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

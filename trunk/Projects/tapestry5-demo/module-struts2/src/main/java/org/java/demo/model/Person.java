package org.java.demo.model;

import javax.persistence.Entity;

import org.java.demo.model.core.NumericIdEntity;

@Entity
public class Person extends NumericIdEntity {

    private String lastName;
    private String firstName;

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

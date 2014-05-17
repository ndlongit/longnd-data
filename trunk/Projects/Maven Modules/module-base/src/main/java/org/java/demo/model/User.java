package org.java.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.java.demo.util.AppUtil;

@Entity
@Table(name = "Users")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Account {

    public static final String PROP_GROUPS = "groups";

    public static enum Gender {
        MALE, FEMALE, NA
    }

    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private List<Group> groups;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToMany(targetEntity = Group.class)
    @JoinTable(name = "users_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Transient
    public String getFullName() {
        String fullName = "";

        if (!AppUtil.isNullOrEmpty(firstName)) {
            fullName += firstName;
        }

        if (!AppUtil.isNullOrEmpty(lastName)) {
            fullName += " " + lastName;
        }

        if (!AppUtil.isNullOrEmpty(middleName)) {
            fullName += " " + middleName;
        }

        return fullName.trim();
    }
}

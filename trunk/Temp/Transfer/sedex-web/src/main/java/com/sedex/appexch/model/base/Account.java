package com.sedex.appexch.model.base;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sedex.appexch.model.Group;
import com.sedex.appexch.model.Role;
import com.sedex.appexch.util.AppUtil;

@Entity
@Table(name = "Accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends NumericIdEntity {

    public static final String PROP_LOGIN_NAME = "loginName";

    public static final String PROP_FIRST_NAME = "firstName";
    public static final String PROP_LAST_NAME = "lastName";
    public static final String PROP_EMAIL = "email";

    public static final String PROP_GROUPS = "groups";
    public static final String PROP_ROLES = "roles";

    private String password;
    private String loginName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;

    public static enum Gender {
        MALE, FEMALE, NA
    }

    private Gender gender;

    private List<Group> groups;
    private List<Role> roles;

    @Column(name = "login_name", unique = true, nullable = false, length = 20)
    @NotNull
    /* For validation */
    @Size(min = 6, max = 20)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @ManyToMany(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    @Transient
    @Override
    public String toString() {
        return "Id: " + getId() + "; LoginName: " + getLoginName();
    }
}

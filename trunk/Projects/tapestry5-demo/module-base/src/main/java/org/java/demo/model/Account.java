package org.java.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.java.demo.model.core.NumericIdEntity;
import org.java.demo.util.AppUtil;

@Entity
@Table(name = "Accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account extends NumericIdEntity {

    public static final String PROP_LOGIN_NAME = "loginName";

    private String loginName;
    private String password;

    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;

    private String email;
    private String phoneNumber;

    private List<String> roles;

    @Column(unique = true)
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

    @ElementCollection
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roleList) {
        this.roles = roleList;
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
    public static void encryptPassword(Account account, String encryptedPassword) {
        String plainPassword = account.getPassword();
        if (!AppUtil.isNullOrEmpty(plainPassword)) {
            account.setPassword(encryptedPassword);
        }
    }
}

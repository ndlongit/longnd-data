package org.java.demo.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.java.demo.util.AppUtil;

@Entity
@Table(name = "Users")
// @AttributeOverride(name = "loginName", column = @Column(name = "login_name"))
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Account {

    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;

    private String email;
    private String phoneNumber;

    private Address tempAddress;
    private Address permanentAddress;

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

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "addNumber", column = @Column(name = "temp_addNumber")),
            @AttributeOverride(name = "street", column = @Column(name = "temp_street")),
            @AttributeOverride(name = "district", column = @Column(name = "temp_district")),
            @AttributeOverride(name = "province", column = @Column(name = "temp_province")),
            @AttributeOverride(name = "country", column = @Column(name = "temp_country")) })
    public Address getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(Address tempAddress) {
        this.tempAddress = tempAddress;
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

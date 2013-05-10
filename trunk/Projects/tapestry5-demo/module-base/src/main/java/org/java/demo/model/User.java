package org.java.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
// @AttributeOverride(name = "loginName", column = @Column(name = "login_name"))
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Account {

    private Address tempAddress;
    private Address permanentAddress;

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
            @AttributeOverride(name = "province", column = @Column(name = "temp_province")) })
    public Address getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(Address tempAddress) {
        this.tempAddress = tempAddress;
    }
}

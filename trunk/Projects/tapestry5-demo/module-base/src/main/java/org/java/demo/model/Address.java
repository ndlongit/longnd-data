package org.java.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Address {

    private String addNumber;
    private String street;
    private String district;
    private String province;
    private String country;

    public String getAddNumber() {
        return addNumber;
    }

    public void setAddNumber(String addNumber) {
        this.addNumber = addNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Transient
    public String getFullAddress() {
        return addNumber + street; // and more...
    }
}

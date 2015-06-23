package org.java.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Address {

    private String addrNumber;
    private String street;
    private String district;
    private String province;
    private String country;

    public String getAddrNumber() {
        return addrNumber;
    }

    public void setAddrNumber(String addrNumber) {
        this.addrNumber = addrNumber;
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
        return addrNumber + street; // and more...
    }
}

package org.java.demo.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String addNumber;
	private String street;
	private String district;
	private String province;

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
}

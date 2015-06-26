package com.sedex.appexch.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sedex.appexch.model.base.SimpleEntity;

@Entity
@Table(name = "app_info")
@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "app_name", nullable = false, unique = true))
public class AppInfo extends SimpleEntity {
	private String logo;
	private String provider;
	private Double ratingValue;
	/** Currently is free of charge */
	private Double price = 0.0;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Transient
	public Double getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Double ratingValue) {
		this.ratingValue = ratingValue;
	}
}

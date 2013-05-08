package org.java.demo.model;

import javax.persistence.MappedSuperclass;

import org.java.demo.model.core.NumericIdEntity;

@MappedSuperclass
public abstract class PersonalGroup extends NumericIdEntity {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

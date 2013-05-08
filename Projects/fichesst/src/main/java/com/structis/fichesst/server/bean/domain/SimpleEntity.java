package com.structis.fichesst.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@MappedSuperclass
public abstract class SimpleEntity extends NumericIdEntity {

	private static final long serialVersionUID = 1L;
	
	//Properties names
	public static final String PROP_LABEL = "label";

	private String label;

	@Column(name = "LIBELLE")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

package org.java.demo.model.core;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class CompositeIdEntity<ID extends Serializable> extends AbstractOrderEntity<ID> {

	private static final long serialVersionUID = 1L;

	private ID id;

	@EmbeddedId
	@Override
	public ID getId() {
		return this.id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;
	}

	@Transient
	@Override
	public boolean isTransient() {
		return getId() == null;
	}
}

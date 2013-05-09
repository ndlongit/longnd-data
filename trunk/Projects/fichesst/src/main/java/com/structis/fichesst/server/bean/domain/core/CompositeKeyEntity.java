package com.structis.fichesst.server.bean.domain.core;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.structis.fichesst.server.bean.core.AbstractOrderEntity;

@MappedSuperclass
public class CompositeKeyEntity<ID extends Serializable> extends AbstractOrderEntity<ID> {

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
}

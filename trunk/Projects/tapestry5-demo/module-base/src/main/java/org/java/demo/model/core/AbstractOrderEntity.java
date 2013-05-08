package org.java.demo.model.core;

import java.io.Serializable;

import javax.persistence.Transient;

public abstract class AbstractOrderEntity<ID extends Serializable> implements BasicEntity<ID> {

	@Override
	@Transient
	public String getOrderByClause() {
		return " Order By " + BasicEntity.PROP_ID;
	}
}

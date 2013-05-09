package com.structis.fichesst.server.bean.core;

import java.io.Serializable;

import javax.persistence.Transient;

import com.structis.fichesst.server.bean.domain.core.BasicEntity;

public abstract class AbstractOrderEntity<ID extends Serializable> implements BasicEntity<ID> {

	@Override
	@Transient
	public String getOrderByClause() {
		return " Order By " + BasicEntity.PROP_ID;
	}
}

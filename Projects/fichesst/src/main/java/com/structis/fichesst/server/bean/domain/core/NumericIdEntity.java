package com.structis.fichesst.server.bean.domain.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.structis.fichesst.server.bean.core.AbstractOrderEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class NumericIdEntity extends AbstractOrderEntity<Integer> {

	private Integer id;

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	@Transient
	public String getOrderByClause() {
		return " order by " + BasicEntity.PROP_ID;
	}
}

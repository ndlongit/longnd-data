package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "DTY_DELEGATION_TYPE")
public class DelegationType extends AbstractShowAbleBean implements
		Identifiable<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "dty_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "dty_name")
	private String name;

	@Column(name = "dty_description")
	private String description;

	@Override
	public Integer getPrimaryKey() {
		return getId();
	}

	@Override
	public void setPrimaryKey(Integer id) {
		setId(id);
	}

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

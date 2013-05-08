package org.java.demo.model.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class NumericIdEntity extends AbstractOrderEntity<Long> {

	private Long id;

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@Transient
	public boolean isTransient() {
		return (getId() == null || getId() <= 0);
	}

	@Override
	@Transient
	public String getOrderByClause() {
		return " order by " + BasicEntity.PROP_ID;
	}
}

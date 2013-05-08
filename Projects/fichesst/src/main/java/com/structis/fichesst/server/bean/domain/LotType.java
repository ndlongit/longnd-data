package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@Entity
@Table(name = "REF_TYPE_LOT")
@AttributeOverride(name = "id", column = @Column(name = "ID_TYPE_LOT"))
public class LotType extends NumericIdEntity {

	private String name;

	private Integer order;

	private Boolean includedInTotal;

	@Column(name = "nom")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ordre")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name = "inclus_dans_total")
	public Boolean getIncludedInTotal() {
		return includedInTotal;
	}

	public void setIncludedInTotal(Boolean includedInTotal) {
		this.includedInTotal = includedInTotal;
	}
}

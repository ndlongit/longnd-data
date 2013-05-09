package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.core.Orderable;

@Entity
@Table(name = "REF_TYPE_MCH_AV")
@AttributeOverride(name = "id", column = @Column(name = "id_type_mch_av"))
public class RefTypeMchAv extends SimpleEntity implements Orderable {

	private static final long serialVersionUID = 1L;

	private Integer order;

	@Override
	@Column(name = "ORDRE")
	public Integer getOrder() {
		return order;
	}

	@Override
	public void setOrder(Integer order) {
		this.order = order;
	}
}

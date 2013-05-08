package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REF_TYPE_MCH_AV")
@AttributeOverride(name = "id", column = @Column(name = "id_type_mch_av"))
public class RefTypeMchAv extends SimpleEntity {

	private static final long serialVersionUID = 1L;
}

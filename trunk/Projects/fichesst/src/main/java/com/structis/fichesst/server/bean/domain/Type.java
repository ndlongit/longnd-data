package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REF_TYPE_BUDJ_CONF")
@AttributeOverride(name = "id", column = @Column(name = "ID_TYPE_BUDG_CONF"))
public class Type extends SimpleEntity {
}

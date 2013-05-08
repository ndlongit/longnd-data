package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REF_DDE_AGREMENT")
@AttributeOverride(name = "id", column = @Column(name = "ID_DDE_AGREMENT"))
public class RefDdeAgrement extends SimpleEntity {
}

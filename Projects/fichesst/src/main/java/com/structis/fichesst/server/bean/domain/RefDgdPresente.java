package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REF_DGD_PRESENTE")
@AttributeOverride(name = "id", column = @Column(name = "ID_DGD_PRESENTE"))
public class RefDgdPresente extends SimpleEntity {
}

package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REF_STATUT")
@AttributeOverride(name = "id", column = @Column(name = "ID_STATUT"))
public class Status extends SimpleEntity {

	private static final long serialVersionUID = 1L;
	
	private String infoLibelle;

	public void setInfoLibelle(String infoLibelle) {
		this.infoLibelle = infoLibelle;
	}
	
	@Column(name="info_libelle")
	public String getInfoLibelle() {
		return infoLibelle;
	}

}

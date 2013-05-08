package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@Entity
@Table(name = "Lot")
@AttributeOverride(name = "id", column = @Column(name = "ID_LOT"))
public class Lot extends NumericIdEntity {

	private static final long serialVersionUID = 1L;
	
	//Properties names
	public static final String PROP_NAME = "name";

	private String name;

	private Chantier chantier;

	//FIXME
	private String siTravauxId = DEFAULT_ID_REF_SITRAVAUX;

	public Lot() {
	}

	@Column(name = "nom")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "id_chantier")
	public Chantier getChantier() {
		return this.chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}

	@Column(name = "id_ref_si_travaux")
	public String getSiTravauxId() {
		return siTravauxId;
	}

	public void setSiTravauxId(String siTravauxId) {
		this.siTravauxId = siTravauxId;
	}
}
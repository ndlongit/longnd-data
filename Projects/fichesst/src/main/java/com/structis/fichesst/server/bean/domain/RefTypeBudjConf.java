package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REF_TYPE_BUDJ_CONF")
@AttributeOverride(name = "id", column = @Column(name = "ID_TYPE_BUDG_CONF"))
public class RefTypeBudjConf extends SimpleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<LigTransfertPP> ligTransfertPps;

	public RefTypeBudjConf() {
	}

	@OneToMany(mappedBy = "refTypeBudjConf")
	public List<LigTransfertPP> getLigTransfertPps() {
		return this.ligTransfertPps;
	}

	public void setLigTransfertPps(List<LigTransfertPP> ligTransfertPps) {
		this.ligTransfertPps = ligTransfertPps;
	}
}
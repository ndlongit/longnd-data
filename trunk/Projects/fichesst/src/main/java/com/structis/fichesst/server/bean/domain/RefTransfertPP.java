package com.structis.fichesst.server.bean.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.core.Orderable;

/**
 * The persistent class for the Ref_Transfert_PP database table.
 */
@Entity
@Table(name = "Ref_Transfert_PP")
@AttributeOverride(name = "id", column = @Column(name = "id_transfert_pp"))
public class RefTransfertPP extends SimpleEntity implements Orderable {
	private static final long serialVersionUID = 1L;

	private List<FicheTransfertpp> ficheTransfertPps;

	private Integer order;

	public RefTransfertPP() {
	}

	@OneToMany(mappedBy = "refTransfertPp")
	public List<FicheTransfertpp> getFicheTransfertPps() {
		return this.ficheTransfertPps;
	}

	public void setFicheTransfertPps(List<FicheTransfertpp> ficheTransfertPps) {
		this.ficheTransfertPps = ficheTransfertPps;
	}

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
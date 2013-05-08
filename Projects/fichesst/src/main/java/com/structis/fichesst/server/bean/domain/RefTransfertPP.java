package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

import java.util.List;


/**
 * The persistent class for the Ref_Transfert_PP database table.
 * 
 */
@Entity
@Table(name="Ref_Transfert_PP")
@AttributeOverride(name = "id", column = @Column(name = "id_transfert_pp"))
public class RefTransfertPP extends SimpleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<FicheTransfertpp> ficheTransfertPps;

	public RefTransfertPP() {
	}

	//bi-directional many-to-one association to Fiche_Transfert_PP
	@OneToMany(mappedBy="refTransfertPp")
	public List<FicheTransfertpp> getFicheTransfertPps() {
		return this.ficheTransfertPps;
	}

	public void setFicheTransfertPps(List<FicheTransfertpp> ficheTransfertPps) {
		this.ficheTransfertPps = ficheTransfertPps;
	}

}
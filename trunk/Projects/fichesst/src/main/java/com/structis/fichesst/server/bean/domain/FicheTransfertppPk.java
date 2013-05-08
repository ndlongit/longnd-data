package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Fiche_Transfert_PP database table.
 * 
 */
@Embeddable
public class FicheTransfertppPk implements Serializable {
	//default serial version id, required for serializable classes.
	private Integer idChantier;
	private Integer idTransfertPp;

	public FicheTransfertppPk() {
	}

	@Column(name="id_chantier")
	public Integer getIdChantier() {
		return this.idChantier;
	}
	public void setIdChantier(int idChantier) {
		this.idChantier = idChantier;
	}

	@Column(name="id_transfert_pp")
	public Integer getIdTransfertPp() {
		return this.idTransfertPp;
	}
	public void setIdTransfertPp(Integer idTransfertPp) {
		this.idTransfertPp = idTransfertPp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FicheTransfertppPk)) {
			return false;
		}
		FicheTransfertppPk castOther = (FicheTransfertppPk)other;
		return 
			(this.idChantier == castOther.idChantier)
			&& (this.idTransfertPp == castOther.idTransfertPp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idChantier;
		hash = hash * prime + this.idTransfertPp;
		
		return hash;
	}
	public FicheTransfertppPk(Integer idChantier,Integer idTransfertPp){
		this.idChantier=idChantier;
		this.idTransfertPp=idTransfertPp;
	}
}
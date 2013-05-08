package com.structis.fichesst.server.bean.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.structis.fichesst.server.bean.domain.core.CompositeKeyEntity;
import com.structis.fichesst.server.bean.domain.core.Timestampable;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the Fiche_Transfert_PP database table.
 * 
 */
@Entity
@Table(name = "Fiche_Transfert_PP")
public class FicheTransfertpp extends CompositeKeyEntity<FicheTransfertppPk>
		implements Serializable,Timestampable {
	private static final long serialVersionUID = 1L;
	Date dateModif;
	private Chantier chantier;
	private List<LigTransfertPP> ligTransfertPps;
	private RefTransfertPP refTransfertPp;

	private Double objectif;

	public FicheTransfertpp() {
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chantier", updatable = false, insertable = false)
	public Chantier getChantier() {
		return this.chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}

	@OneToMany(mappedBy = "ficheTransfertPp",fetch = FetchType.EAGER)
	public List<LigTransfertPP> getLigTransfertPps() {
		return this.ligTransfertPps;
	}

	public void setLigTransfertPps(List<LigTransfertPP> ligTransfertPps) {
		this.ligTransfertPps = ligTransfertPps;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transfert_pp", insertable = false, updatable = false)
	public RefTransfertPP getRefTransfertPp() {
		return this.refTransfertPp;
	}

	public void setRefTransfertPp(RefTransfertPP refTransfertPp) {
		this.refTransfertPp = refTransfertPp;
	}

	@Column(name = "objectif")
	public Double getObjectif() {
		return objectif;
	}
	
	@Column(name = "date_modif")
	@Override
	public Date getModifiedDate() {
		return dateModif;
	}

	public void setObjectif(Double objectif) {
		this.objectif = objectif;
	}
	
	@Transient
	public FicheTransfertppPk getFicheTransfertppPk() {
		return getId();
	}

	public void setFicheTransfertppPk(FicheTransfertppPk ficheTransfertppPk) {
		setId(ficheTransfertppPk);
	}

	@Override
	public void setModifiedDate(Date date) {
		this.dateModif=date;
	}
	
	@Override
	public int hashCode() {
		int hashValue = 0;
		Integer chantierId = getChantier().getId();
		if( chantierId != null && chantierId > 0 ) {
			hashValue += chantierId.hashCode();
		}
		Integer transfertPpId = getRefTransfertPp().getId();
		if( transfertPpId != null && transfertPpId > 0 ) {
			hashValue += transfertPpId.hashCode();
		}

		return hashValue;
	}

	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof FicheTransfertpp) ) {
			return false;
		}

		FicheTransfertpp other = (FicheTransfertpp) obj;

		Integer chantierId = getChantier().getId();
		Integer transfertPpId = getRefTransfertPp().getId();

		Integer chantierId2 = other.getChantier().getId();
		Integer transfertPpId2 = getRefTransfertPp().getId();

		return(chantierId != null && chantierId.equals(chantierId2) && transfertPpId != null && transfertPpId.equals(transfertPpId2));
	}
	
}
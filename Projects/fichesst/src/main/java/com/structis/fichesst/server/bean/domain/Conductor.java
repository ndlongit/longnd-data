package com.structis.fichesst.server.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@Entity
@Table(name = "CONDUCTEUR")
@AttributeOverride(name = "id", column = @Column(name = "ID_CONDUCTEUR"))
public class Conductor extends NumericIdEntity {

	private String preName;

	private String name;

	private String idSiTravaux;

	private Integer idChantier;
	private Chantier chantier;
	@Column(name = "prenom")
	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	@Column(name = "nom")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id_ref_si_travaux")
	public String getIdSiTravaux() {
		return idSiTravaux;
	}

	public void setIdSiTravaux(String idSiTravaux) {
		this.idSiTravaux = idSiTravaux;
	}
	
	@Column(name = "id_chantier")
	public Integer getIdChantier() {
		return idChantier;
	}

	public void setIdChantier(Integer chantierId) {
		this.idChantier = chantierId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@JoinColumn(name="id_chantier",insertable=false,updatable=false)
	public Chantier getChantier() {
		return this.chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}

}

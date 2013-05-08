package com.structis.fichesst.server.bean.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.fichesst.server.bean.domain.core.NumericIdEntity;

@Entity
@Table(name = "LIG_RETENUE")
@AttributeOverride(name = "id", column = @Column(name = "ID_LIG_RETENUE"))
public class Deduction extends NumericIdEntity {

	private Date date;

	private Integer canto;

	private Integer badge;

	private Integer grue;

	private Integer lift;

	private Integer benne;

	private Integer nettoyage;

	private Double autres;

	private Double prorata;

	private Double refacturations;

	private FicheSt ficheSt;

	@Column(name = "date")
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "qte_canto")
	public Integer getCanto() {
		return this.canto;
	}

	public void setCanto(Integer canto) {
		this.canto = canto;
	}

	@Column(name = "qte_badge")
	public Integer getBadge() {
		return this.badge;
	}

	public void setBadge(Integer badge) {
		this.badge = badge;
	}

	@Column(name = "qte_grue")
	public Integer getGrue() {
		return this.grue;
	}

	public void setGrue(Integer grue) {
		this.grue = grue;
	}

	@Column(name = "qte_lift")
	public Integer getLift() {
		return this.lift;
	}

	public void setLift(Integer lift) {
		this.lift = lift;
	}

	@Column(name = "qte_benne")
	public Integer getBenne() {
		return this.benne;
	}

	public void setBenne(Integer benne) {
		this.benne = benne;
	}

	@Column(name = "qte_nettoyage")
	public Integer getNettoyage() {
		return this.nettoyage;
	}

	public void setNettoyage(Integer nettoyage) {
		this.nettoyage = nettoyage;
	}

	@Column(name = "autres")
	public Double getAutres() {
		if(this.autres == null) {
			return 0.0;
		}
		
		return this.autres;
	}

	public void setAutres(Double autres) {
		this.autres = autres;
	}

	@Column(name = "prorata")
	public Double getProrata() {
		if(this.prorata == null) {
			return 0.0;
		}
		
		return this.prorata;
	}

	public void setProrata(Double prorata) {
		this.prorata = prorata;
	}

	@Column(name = "refacturation")
	public Double getRefacturations() {
		return this.refacturations;
	}

	public void setRefacturations(Double refacturation) {
		this.refacturations = refacturation;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FICHE_ST")
	public FicheSt getFicheSt() {
		return ficheSt;
	}

	public void setFicheSt(FicheSt ficheSt) {
		this.ficheSt = ficheSt;
	}
}

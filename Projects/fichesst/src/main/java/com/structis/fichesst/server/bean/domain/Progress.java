package com.structis.fichesst.server.bean.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIG_AVCT")
@AttributeOverride(name = "id", column = @Column(name = "ID_LIG_AVCT"))
public class Progress extends SimpleEntity {
	private Date date;

	private Double cumule;

	private Double cumule2;

	private FicheSt ficheSt;

	@Column(name = "date")
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "cumule_avct")
	public Double getCumule() {
		return this.cumule;
	}

	public void setCumule(Double cumule) {
		this.cumule = cumule;
	}

	@Column(name = "cumule_ret")
	public Double getCumule2() {
		return this.cumule2;
	}

	public void setCumule2(Double cumule2) {
		this.cumule2 = cumule2;
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

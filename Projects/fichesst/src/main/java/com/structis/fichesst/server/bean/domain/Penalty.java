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
@Table(name = "LIG_PENALITE")
@AttributeOverride(name = "id", column = @Column(name = "ID_LIG_PENALITE"))
public class Penalty extends NumericIdEntity {

	private Date date;

	private Double amount;

	private String comment;

	private FicheSt ficheSt;

	@Column(name = "date")
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "montant")
	public Double getAmount() {
		if(this.amount == null) {
			return 0.0;
		}
		
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "commentaires")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

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
@Table(name = "CAUTION_FOURNIE")
@AttributeOverride(name = "id", column = @Column(name = "ID_CAUTION_FOURNIE"))
public class CautionFournie extends NumericIdEntity {

	private static final long serialVersionUID = 1L;

	private Date date;

	private Double amount;

	private FicheSt ficheSt;

	public CautionFournie() {
	}

	@Column(name = "date_caution")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "montant")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FICHE_ST")
	public FicheSt getFicheSt() {
		return ficheSt;
	}

	public void setFicheSt(FicheSt ficheSt) {
		this.ficheSt = ficheSt;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CautionFournie)){
			return false;
		}
		
		CautionFournie other = (CautionFournie)obj;
	    return this.getId().equals(other.getId());
	}
}

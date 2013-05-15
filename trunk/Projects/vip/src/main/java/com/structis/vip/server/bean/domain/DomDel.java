package com.structis.vip.server.bean.domain;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;


/**
 * The persistent class for the LAG_LANGUAGE database table.
 * 
 */
@Entity
@Table(name="DOM_DEL")
public class DomDel  extends AbstractShowAbleBean  implements
Identifiable<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "dom_del_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="dom_del_comment")
	private String comment;
		
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "del_id", nullable = false)
	private Delegation delegation;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "dom_id", nullable = false)
	private DocumentMdl documentMdl;
	
	@Column(name="dom_del_signed_date")
	private Date signedDate;
	
	@Column(name="dom_signed_filename")
	private String signedFilename;
	
	@Column(name="dom_hemera_lien")
	private String hemeraLien;

    public DomDel() {
    }

    @Override
	public Integer getPrimaryKey() {
		return getId();
	}

	@Override
	public void setPrimaryKey(Integer id) {
		setId(id);
	}

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Delegation getDelegation() {
		return delegation;
	}


	public void setDelegation(Delegation delegation) {
		this.delegation = delegation;
	}


	public DocumentMdl getDocumentMdl() {
		return documentMdl;
	}


	public void setDocumentMdl(DocumentMdl documentMdl) {
		this.documentMdl = documentMdl;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	
	public String getSignedFilename() {
		return signedFilename;
	}

	public void setSignedFilename(String signedFilename) {
		this.signedFilename = signedFilename;
	}

	
	public String getHemeraLien() {
		return hemeraLien;
	}

	public void setHemeraLien(String hemeraLien) {
		this.hemeraLien = hemeraLien;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
}


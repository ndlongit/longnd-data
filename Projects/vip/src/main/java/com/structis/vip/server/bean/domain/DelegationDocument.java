package com.structis.vip.server.bean.domain;

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

@Entity
@Table(name = "ODD_OTHER_DELEGATION_DOCUMENT")
public class DelegationDocument extends AbstractShowAbleBean implements Identifiable<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "odd_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "odd_filename")
	private String fileName;

	@Column(name = "odd_description")
	private String description;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "del_id", nullable = false)
	private Delegation delegation;

	public DelegationDocument() {
	}

	@Override
	public Integer getPrimaryKey() {
		return this.getId();
	}

	public boolean isPrimaryKeySet() {
		return (this.getId() != null);
	}

	@Override
	public void setPrimaryKey(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Delegation getDelegation() {
		return delegation;
	}

	public void setDelegation(Delegation delegation) {
		this.delegation = delegation;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
}
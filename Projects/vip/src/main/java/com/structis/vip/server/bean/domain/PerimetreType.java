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
@Table(name = "PTY_PERIMETRE_TYPE") 
public class PerimetreType extends AbstractShowAbleBean implements
Identifiable<String> {
	@Id 	
	@Column(name = "pty_id", unique = true, nullable = false) 
	private String ptyId;
	@Column(name = "pty_name")
	private String name;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = false)
	private Entite entite;
	
	@Column(name = "pty_isSubdelegable")
	private Integer isSubdelegable;
	    
	@Override
	public String getPrimaryKey() {
		return this.getPtyId() ;
	}	

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getPtyId() != null);
	}

	@Override
	public void setPrimaryKey(String id) {
		this.setPtyId(id);
	}
	
	public String getPtyId() {
		return ptyId;
	}
	public void setPtyId(String id) {
		this.ptyId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	
	public Integer getIsSubdelegable() {
		return isSubdelegable;
	}

	public void setIsSubdelegable(Integer isSubdelegable) {
		this.isSubdelegable = isSubdelegable;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
//		checkDataToString("id", id.toString(), sb);
//		checkDataToString("name", name.toString(), sb);		
	}
	
}
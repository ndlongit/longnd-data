package com.structis.vip.server.bean.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "ENT_ENTITE")
public class Entite extends AbstractShowAbleBean implements
		Identifiable<String> {
	@Id
	@Column(name = "ent_id", unique = true, nullable = false)
	private String entId;
	
	@Column(name = "ent_name")
	private String name;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_default_lang", nullable = true)
	private Language language = new Language();
	
	@Override
	public String getPrimaryKey() {
		return this.getEntId() ;
	}	

	@Override
	public boolean isPrimaryKeySet() {
		return (this.getEntId() != null);
	}

	@Override
	public void setPrimaryKey(String id) {
		this.setEntId(id);
	} 
	public String getEntId() {
		return entId;
	}


	public void setEntId(String id) {
		this.entId = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
}

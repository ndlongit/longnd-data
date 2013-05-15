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
@Table(name = "CON_CONTROL_TYPE") 
public class ControlType extends AbstractShowAbleBean implements
Identifiable<Integer> {
	@Id 
	@GeneratedValue
	@Column(name = "con_id", unique = true, nullable = false) 
	private Integer id;
	
	@Column(name = "con_libelle")
	private String label;
	
	@Column(name = "con_description")
	private String description;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = false)
	private Entite entite = new Entite();

	@Override
	public Integer getPrimaryKey() {
		return this.getId() ;
	}	

	@Override
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
	
	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
	
}

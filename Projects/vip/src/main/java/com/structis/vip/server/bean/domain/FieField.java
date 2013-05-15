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
@Table(name = "FIE_FIELD")
public class FieField extends AbstractShowAbleBean implements
		Identifiable<Integer> {
	@Id
	@Column(name = "fie_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "fie_form_field_id")
	private String formFieldId;
	
	@Column(name = "fie_label")
	private String label;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "lag_id", nullable = false)
	private Language language = new Language();
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ent_id", nullable = false)
	private Entite entite;
	
	@Column(name = "fie_db_field")
	private String dbField;        
	
	@Column(name = "fie_group")
	private String group;
	
	@Column(name = "fie_order")
	private Integer order;

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
	
	public String getFormFieldId() {
		return formFieldId;
	}

	public void setFormFieldId(String formFieldId) {
		this.formFieldId = formFieldId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public String getDbField() {
		return dbField;
	}

	public void setDbField(String dbField) {
		this.dbField = dbField;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}

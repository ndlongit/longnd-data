package com.structis.vip.server.bean.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "FIR_FIELD_RULE")
public class FieldRule extends AbstractShowAbleBean implements
		Identifiable<Integer> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "fir_id", unique = true, nullable = false)	
	private Integer id;
		
	@Column(name = "dem_group", nullable = false)
	private Integer group;
	
	@Column(name = "fir_displayed")
	private Integer isDisplayed;
	
	@Column(name = "fir_required")
	private Integer isRequired;
      
    @ManyToOne(cascade = { CascadeType.REFRESH })
  	@JoinColumn(name = "fie_id", nullable = false)
  	private FieField field = new FieField();      
       
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
	
	
	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getIsDisplayed() {
		return isDisplayed;
	}

	public void setIsDisplayed(Integer isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	public FieField getField() {
		return field;
	}

	public void setField(FieField field) {
		this.field = field;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
}

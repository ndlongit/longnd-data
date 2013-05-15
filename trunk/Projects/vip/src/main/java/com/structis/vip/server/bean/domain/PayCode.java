package com.structis.vip.server.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.structis.vip.server.bean.core.AbstractShowAbleBean;
import com.structis.vip.server.bean.domain.core.Identifiable;

@Entity
@Table(name = "PAC_PAY_CODE") 
public class PayCode extends AbstractShowAbleBean implements
Identifiable<Integer> {
	@Id 
	@GeneratedValue
	@Column(name = "pac_id", unique = true, nullable = false) 
	private Integer id;
	
	@Column(name = "pac_code")
	private String code;
	
	@Column(name = "pac_name")
	private String name;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected void beanToString(StringBuffer sb) {
	}
	
}

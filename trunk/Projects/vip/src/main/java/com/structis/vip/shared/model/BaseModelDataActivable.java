/**
 * 
 */
package com.structis.vip.shared.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Class abstraite de la classe model
 * @author b.brotosumpeno
 *
 */
public abstract class BaseModelDataActivable extends BaseModelData implements ModelActivable {
	
	private static final long serialVersionUID = 1L;
	 
	public void setId(Integer id){
		set(BASE_ID, id);
	}
	
	public String getLibelle(){
		return get(BASE_LIBELLE);
	}
	
	public void setLibelle(String libelle){
		set(BASE_LIBELLE, libelle);
	}

	public Boolean getActive() {		
		return get(BASE_ACTIVE);
	}

	public void setActive(Boolean active) {
		set(BASE_ACTIVE, active);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getId(){
		return get(BASE_ID);
	}
	
	public void setDateSuppr(Date dateSuppr) {
		set(BASE_DATESUPPR, dateSuppr);
	}
	
	public Date getDateSuppr(){
		return get(BASE_DATESUPPR);
	}

}

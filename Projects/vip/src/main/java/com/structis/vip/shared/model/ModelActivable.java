package com.structis.vip.shared.model;

import java.util.Date;

public interface ModelActivable {
	
	// Les constant pour tous les models activable
	public static final String BASE_ID = "id";
	public static final String BASE_LIBELLE = "libelle";
	public static final String BASE_ACTIVE = "active";
	public static final String BASE_DATESUPPR = "dateSuppr";
	
	// Les m�thodes des getters
	public Integer getId();

	public void setId(Integer id);
	
	public Date getDateSuppr();
	
	public void setDateSuppr(Date dateSuppr);
	
	public Boolean getActive();
	
	public void setActive(Boolean active);
	
	public String getLibelle();
	
	public void setLibelle(String libelle);

}

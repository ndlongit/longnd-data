package com.structis.vip.shared.model;

import java.util.Date;

public class ChantierTypeModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String CTY_ID = "id";
	public static final String CTY_LABEL = "label";
	public static final String CTY_ENDDATE = "endDate";
	public static final String CTY_IS_SUBDELEGABLE = "isSubdelegable";
	public static final String CTY_ENTITE = "entite";

	@SuppressWarnings("unused")
	private EntiteModel entiteModel;

	public Integer getId() {
		return get(CTY_ID);
	}

	public void setId(Integer id) {
		set(CTY_ID, id);
	}

	public String getLabel() {
		return get(CTY_LABEL);
	}

	public void setLabel(String label) {
		set(CTY_LABEL, label);
	}

	public Date getEndDate() {
		return get(CTY_ENDDATE);
	}

	public void setEndDate(Date endDate) {
		set(CTY_ENDDATE, endDate);
	}
	
	public Integer getIsSubdelegable() {
		return get(CTY_IS_SUBDELEGABLE);
	}

	public void setIsSubdelegable(Integer isSubdelegable) {
		set(CTY_IS_SUBDELEGABLE, isSubdelegable);
	}

	public EntiteModel getEntite() {
		return get(CTY_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(CTY_ENTITE, entite);
	}
}
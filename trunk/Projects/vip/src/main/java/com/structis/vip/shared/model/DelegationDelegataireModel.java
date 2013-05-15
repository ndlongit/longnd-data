package com.structis.vip.shared.model;

public class DelegationDelegataireModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String DED_ID = "id";
	public static final String DED_COL_ID = "colId";
	public static final String DED_DEL_ID = "delId";
	public static final String DED_COL_NAME = "colName";

	public Integer getId() {
		return get(DED_ID);
	}

	public void setId(Integer id) {
		set(DED_ID, id);
	}
	
	public Integer getDelId() {
		return get(DED_DEL_ID);
	}

	public void setDelId(Integer delId) {
		set(DED_DEL_ID, delId);
	}

	public Integer getColId() {
		return get(DED_COL_ID);
	}

	public void setColId(Integer colId) {
		set(DED_COL_ID, colId);
	}
	
	public String getColName() {
		return get(DED_COL_NAME);
	}

	public void setColName(String colName) {
		set(DED_COL_NAME, colName);
	}

}
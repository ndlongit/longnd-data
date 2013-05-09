package com.structis.fichesst.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

public abstract class AbstractDto extends BaseModel {

	private static final long serialVersionUID = 1L;

	public static final String ID = "id";

	public static final String ID_SI_TRAVAUX = "idSiTravaux";

	public static final String DEFAULT_ID_SI_TRAVAUX = "";

	protected AbstractDto() {
		setIdSiTravaux(DEFAULT_ID_SI_TRAVAUX);
	}

	public Integer getId() {
		return get(ID);
	}

	public void setId(Integer id) {
		set(ID, id);
	}

	public String getIdSiTravaux() {
		return get(ID_SI_TRAVAUX, "");
	}

	public void setIdSiTravaux(String idRefSiTravaux) {
		this.set(ID_SI_TRAVAUX, idRefSiTravaux);
	}
	
	public void initData() {

		//Call method below in order to initialize test data
				initTestData();
	}
	
	protected void initTestData() {
		//Sub-classes implement this method in order to initialize test data.
	}
}

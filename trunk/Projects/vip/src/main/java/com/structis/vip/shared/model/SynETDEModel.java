package com.structis.vip.shared.model;

public class SynETDEModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 5567311460418598088L;
	
	public static final String SYNC_ETDE_CODE = "code";
	public static final String SYNC_ETDE_NAME = "name";

	public String getCode() {
		return get(SYNC_ETDE_CODE);
	}

	public void setCode(String code) {
		set(SYNC_ETDE_CODE, code);
	}

	public String getName() {
		return get(SYNC_ETDE_NAME);
	}

	public void setName(String name) {
		set(SYNC_ETDE_NAME, name);
	}
}
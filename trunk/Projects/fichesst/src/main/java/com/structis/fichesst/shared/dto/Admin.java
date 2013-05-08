package com.structis.fichesst.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

public class Admin extends BaseModel {
	public Admin() {
	}

	// first is property nam ,second is the value
	public Admin(String name) {
		set("name", name);

	}

	public String getName() {
		return (String) get("name");
	}

	public String toString() {
		return getName();
	}
}
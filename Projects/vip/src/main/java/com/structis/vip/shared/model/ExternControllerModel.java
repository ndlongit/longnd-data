package com.structis.vip.shared.model;
public class ExternControllerModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;

	public static final String EXC_ID = "id";
	public static final String EXC_NAME = "name";
	public static final String EXC_ADDRESS = "address";

	public Integer getId() {
		return get(EXC_ID);
	}

	public void setId(Integer id) {
		set(EXC_ID, id);
	}


	public String getName() {
		return get(EXC_NAME);
	}
	
	public void setName(String name) {
		set(EXC_NAME, name);
	}
	public String getAddress() {
		return get(EXC_ADDRESS);
	}

	public void setAddress(String address) {
		set(EXC_ADDRESS, address);
	}
	
}
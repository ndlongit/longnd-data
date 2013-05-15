package com.structis.vip.server.bean.domain.core.business;

public class KeyValueVM {
	private String key;
	private String value;	
	private String type;
	public KeyValueVM(String key, String libelle) {
		super();
		this.key = key;
		this.value = libelle;
		
	}
	
	public KeyValueVM(String key, String value, String type) {
		super();
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

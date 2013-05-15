package com.structis.vip.shared.model;

public class KeyValueModel extends BaseModelDataActivable{

	private static final long serialVersionUID = 1L;
	public final static String KEY = "key";
	public final static String VALUE = "value";
	public final static String TYPE = "type";
	
	public String getKey() {
		return get(KEY);
	}
	public String getValue() {
		return get(VALUE);
	}
	public void setKey(String key) {
		set(KEY, key);
	}
	public void setValue(String value) {
		set(VALUE, value);
	}
	public String getType() {
		return get(TYPE);
	}

	public void setType(String type) {
		set(TYPE, type);
	}
}

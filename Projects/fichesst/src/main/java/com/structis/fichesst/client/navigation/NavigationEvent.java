package com.structis.fichesst.client.navigation;

import java.util.HashMap;
import java.util.Map;

public class NavigationEvent {

	private Object object;

	private Map<String, String> source;

	private boolean load = true;

	public NavigationEvent(Object object) {
		this.object = object;
	}

	public NavigationEvent() {
		source = new HashMap<String, String>();
	}

	public NavigationEvent(String key, String value) {
		super();
		source = new HashMap<String, String>();
		source.put(key, value);
	}

	public NavigationEvent(String key, Integer value) {
		super();
		source = new HashMap<String, String>();
		source.put(key, value + "");
	}

	public NavigationEvent(Map<String, String> source) {
		super();
		this.source = source;
	}

	public NavigationEvent(Map<String, String> source, boolean load) {
		super();
		this.source = source;
		this.load = load;
	}

	public Map<String, String> getParameters() {
		return source;
	}

	public String getParameter(String key) {
		if( null == source ) {
			return null;
		}
		return source.get(key);
	}

	public boolean getLoad() {
		return load;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}

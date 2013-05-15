package com.structis.vip.client.view;

import java.util.HashMap;

public class ViewState {

	private HashMap<String, String> parameters = new HashMap<String, String>();

	public ViewState() {
	}
	
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	
	public void addParameter(final String key, final String value) {
		parameters.put(key, value);
	}
	
	public String getParameterValue(final String key) {
		return parameters.get(key);
	}
	
	public Integer getParameterValueAsInteger(final String key) {
		return Integer.valueOf(parameters.get(key));
	}
	
	public void clear() {
		parameters.clear();
	}
	
}

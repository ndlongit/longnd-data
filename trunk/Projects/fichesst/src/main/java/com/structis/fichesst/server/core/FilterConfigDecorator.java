package com.structis.fichesst.server.core;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

public class FilterConfigDecorator implements FilterConfig {
	
	private FilterConfig decorated;
	private Map<String, String> params;
	
	public FilterConfigDecorator(FilterConfig decorated, 
			Map<String, String> initParams) {
		this.decorated = decorated;
		this.params = initParams;
	}

	public String getFilterName() {
		return decorated.getFilterName();
	}

	public String getInitParameter(String arg0) {
		String result = decorated.getInitParameter(arg0);
		if (null == result) {
			result = params.get(arg0);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getInitParameterNames() {
		return decorated.getInitParameterNames();
	}

	public ServletContext getServletContext() {
		return decorated.getServletContext();
	}

}

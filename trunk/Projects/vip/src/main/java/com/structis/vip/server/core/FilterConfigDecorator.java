package com.structis.vip.server.core;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

public class FilterConfigDecorator implements FilterConfig {

    private FilterConfig decorated;
    private Map<String, String> params;

    public FilterConfigDecorator(FilterConfig decorated, Map<String, String> initParams) {
        this.decorated = decorated;
        this.params = initParams;
    }

    @Override
    public String getFilterName() {
        return this.decorated.getFilterName();
    }

    @Override
    public String getInitParameter(String arg0) {
        String result = this.decorated.getInitParameter(arg0);
        if (null == result) {
            result = this.params.get(arg0);
        }
        return result;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Enumeration getInitParameterNames() {
        return this.decorated.getInitParameterNames();
    }

    @Override
    public ServletContext getServletContext() {
        return this.decorated.getServletContext();
    }

}

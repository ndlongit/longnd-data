package com.structis.vip.server.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

public void init (FilterConfig filterConfig) throws ServletException {
}

public void destroy () {
}

public void doFilter (ServletRequest request, ServletResponse response, FilterChain filterChain)
throws IOException, ServletException {

	HttpServletRequest httpRequest = (HttpServletRequest)request;
	String servletPath = httpRequest.getServletPath();
	if (servletPath!=null && (servletPath.contains("ReportViewer.aspx")) ) {
		HttpServletResponse httpResponse = (HttpServletResponse)response;	
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setHeader("Cache-control", "no-cache, no-store");
	}
	
	filterChain.doFilter(request, response);
	}

}


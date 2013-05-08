package com.structis.fichesst.server.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.structis.fichesst.server.core.FilterConfigDecorator;
import com.structis.fichesst.server.core.SpringGetter;

import net.sourceforge.spnego.SpnegoHttpFilter;

public class SpnegoWrapperFilter implements Filter {
	
	private SpnegoHttpFilter spnegoHttpFilter;

	public void destroy() {
		if (null != spnegoHttpFilter) {
			spnegoHttpFilter.destroy();
		}
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if (null == spnegoHttpFilter) {
			arg2.doFilter(arg0, arg1);
		}
		else {
			spnegoHttpFilter.doFilter(arg0, arg1, arg2);
		}
	}

	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			Map<String, String> map = (Map<String, String>) SpringGetter.getBean(filterConfig.getServletContext(), "mapSpnego");
			String spnegoActif = map.get("spnego.active");
			if (spnegoActif != null && spnegoActif.equals("true")) {
				FilterConfigDecorator fcd = new FilterConfigDecorator(filterConfig, map);
				spnegoHttpFilter = new SpnegoHttpFilter();
				spnegoHttpFilter.init(fcd);
			}
		}
		catch (Throwable e) {
			spnegoHttpFilter = null;
			e.printStackTrace();
		}
	}

}

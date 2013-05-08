package com.structis.fichesst.server.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityProvider {
	String getUserName(HttpServletRequest request);
}

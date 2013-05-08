package com.structis.fichesst.server.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class KerberosSecurityProvider implements SecurityProvider {

	private static Logger logger = Logger.getLogger(KerberosSecurityProvider.class);

	public String getUserName(HttpServletRequest request) {
		if( logger.isDebugEnabled() ) {
			logger.debug("Start getUsername");
		}

		String userName = null;
		if( request.getRemoteUser() != null ) {
			userName = request.getRemoteUser();
		}
		else if( request.getUserPrincipal() != null ) {
			userName = request.getUserPrincipal().getName();
		}

		if( logger.isDebugEnabled() ) {
			logger.debug("Login : " + userName);
		}

		return userName;
	}

}

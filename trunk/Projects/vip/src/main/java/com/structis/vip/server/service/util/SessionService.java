package com.structis.vip.server.service.util;

import javax.servlet.http.HttpSession;

import com.structis.vip.shared.model.UserModel;


public interface SessionService {

	boolean openSession(HttpSession httpSession, UserModel userContext);
		
	void killSession(final UserModel userContext);
	
}

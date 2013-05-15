package com.structis.vip.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.AppException;
import com.structis.vip.shared.model.UserModel;

@RemoteServiceRelativePath("springGwtServices/clientUserContextService")
public interface ClientUserContextService extends RemoteService{
	
	UserModel checkUserByKerberosSSO() throws AppException;
	
	UserModel findUserOnDomainBC(final String login) throws AppException;
	
	void disconnectUser(final UserModel userContext);
}

package com.structis.vip.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.UserModel;

@RemoteServiceRelativePath("springGwtServices/clientAuthenticationService")
public interface ClientAuthenticationService extends RemoteService {

    UserModel login(String userName, String passWord);

    UserModel ssoLogin();

    void ssoLogout();

}

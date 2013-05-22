package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.RoleModel;
import com.structis.vip.shared.model.UserModel;

@RemoteServiceRelativePath("springGwtServices/clientRoleService")
public interface ClientRoleService extends RemoteService {

    List<RoleModel> getRoles(UserModel userModel);
}

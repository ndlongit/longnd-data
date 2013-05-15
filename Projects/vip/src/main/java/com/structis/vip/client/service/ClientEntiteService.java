package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

@RemoteServiceRelativePath("springGwtServices/clientEntiteService")
public interface ClientEntiteService extends RemoteService {
	List<EntiteModel> getAllEntites();
	EntiteModel getEntiteByUser(UserModel user);
	Boolean insert(EntiteModel entiteModel);
	Boolean update(EntiteModel entiteModel);
	EntiteModel findById(String id);
	List<PerimetreTreeModel> getTreeModelById(String id, List<UserRoleModel> userRoles);
}

package com.structis.fichesst.client.service;



import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.RoleModel;
@RemoteServiceRelativePath("springGwtServices/clientRoleService")
public interface ClientRoleService extends RemoteService {
	RoleModel findRoleById(Integer idChantier,Integer idUser);
	List<RoleModel> findRolesByIdUser(Integer idUser);
}

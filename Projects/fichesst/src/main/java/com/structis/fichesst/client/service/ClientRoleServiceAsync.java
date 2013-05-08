package com.structis.fichesst.client.service;



import java.util.List;

import com.structis.fichesst.shared.dto.RoleModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
public interface ClientRoleServiceAsync {
	void findRoleById(Integer idChantier,Integer idUser, AsyncCallback<RoleModel> callback);
	void findRolesByIdUser(Integer idUser, AsyncCallback<List<RoleModel>> callback);
}

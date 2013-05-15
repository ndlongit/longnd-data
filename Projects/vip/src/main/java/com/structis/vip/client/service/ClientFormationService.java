package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.FormationModel;

@RemoteServiceRelativePath("springGwtServices/clientFormationService")
public interface ClientFormationService extends RemoteService {
	
	List<FormationModel> findAll();
	List<FormationModel> findByEntite(String entiteId);
	Boolean delete(FormationModel model);	
	FormationModel insert(FormationModel model);
	FormationModel update(FormationModel model);
}
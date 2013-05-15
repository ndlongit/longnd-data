package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.DelegationStatusModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationStatusService")
public interface ClientDelegationStatusService extends RemoteService {
	List<DelegationStatusModel> getAllDelegationStatuses();
	
	DelegationStatusModel findById(Integer id);

	Boolean delete(DelegationStatusModel model);
	DelegationStatusModel insert(DelegationStatusModel model);
	DelegationStatusModel update(DelegationStatusModel model);
}
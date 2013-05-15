package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.FieFieldModel;

@RemoteServiceRelativePath("springGwtServices/clientFieldService")
public interface ClientFieldService extends RemoteService {	
		List<FieFieldModel> getFieldsByEntiteId(String entiteId);		
		FieFieldModel insert(FieFieldModel fieFieldModel);
		List<FieFieldModel> getFieldsByGroupName(String entId, String groupName);
}

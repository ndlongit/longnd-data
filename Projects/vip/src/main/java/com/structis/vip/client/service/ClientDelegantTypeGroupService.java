package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegantTypeGroupService")
public interface ClientDelegantTypeGroupService extends RemoteService {
	List<DelegantTypeGroupModel> getDelegantTypeGroups();
	DelegantTypeGroupModel findById(Integer id);
	
	Boolean delete(DelegantTypeGroupModel model) throws LanguageException;	
	DelegantTypeGroupModel insert(DelegantTypeGroupModel model);
	DelegantTypeGroupModel update(DelegantTypeGroupModel model);
}

package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.ExternControllerModel;

@RemoteServiceRelativePath("springGwtServices/clientExternControllerService")
public interface ClientExternControllerService extends RemoteService {
	
	List<ExternControllerModel> findAll();
	Boolean delete(ExternControllerModel model);	
	ExternControllerModel insert(ExternControllerModel model);
	ExternControllerModel update(ExternControllerModel model);	
}
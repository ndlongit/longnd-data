package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ExtControllerControlModel;

@RemoteServiceRelativePath("springGwtServices/clientExternControllerControlService")
public interface ClientExternControllerControlService extends RemoteService {
	List<ExtControllerControlModel> findByControl(Integer id);
	List<ExtControllerControlModel> findAll();
	Boolean delete(ExtControllerControlModel model);	
	ExtControllerControlModel insert(ExtControllerControlModel model);
	ExtControllerControlModel update(ExtControllerControlModel model);
	Boolean deleteByControl(Integer control);
	void insert(List<ExtControllerControlModel> eccs);
	void insert(Integer controlId, List<ExtControllerControlModel> eccs);
}
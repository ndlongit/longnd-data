package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.CollaborateurTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientCollaborateurTypeService")
public interface ClientCollaborateurTypeService extends RemoteService {
	List<CollaborateurTypeModel> getAllCollaborateurType();
	List<CollaborateurTypeModel> getCollaborateurTypeByEntite(String entiteId);
	Boolean delete(CollaborateurTypeModel model);
	CollaborateurTypeModel insert(CollaborateurTypeModel model);
	CollaborateurTypeModel update(CollaborateurTypeModel model);
}
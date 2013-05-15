package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.LanguageException;

import com.structis.vip.shared.model.DocumentTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientDocTypeService")
public interface ClientDocTypeService extends RemoteService {
	List<DocumentTypeModel> getDocTypes();
	DocumentTypeModel findById(Integer id);
	
	Boolean delete(DocumentTypeModel model) throws LanguageException;	
	DocumentTypeModel insert(DocumentTypeModel model);
	DocumentTypeModel update(DocumentTypeModel model);
}

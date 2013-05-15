package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.DelegationDocumentModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationDocumentService")
public interface ClientDelegationDocumentService extends RemoteService {
	List<DelegationDocumentModel> getDelegationDocuments(Integer delegationId);

	DelegationDocumentModel insert(DelegationDocumentModel delegationDocument);

	Boolean delete(DelegationDocumentModel delegationModel);
}
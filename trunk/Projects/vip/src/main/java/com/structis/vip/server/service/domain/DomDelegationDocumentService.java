package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationDocument;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDelegationDocumentService extends GenericEntityService<DelegationDocument, Integer> {
	List<DelegationDocument> getDelegationDocuments(Integer delegationId);

	DelegationDocument insert(DelegationDocument delegationDocument, String path);
	
	Boolean deleteByDelId(Integer delId, String path);	
}

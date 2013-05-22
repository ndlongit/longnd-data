package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationDocument;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegationDocumentDao extends GenericDao<DelegationDocument, Integer> {

    List<DelegationDocument> getDelegationDocuments(Integer delegationId);

    DelegationDocument insert(DelegationDocument delegationDocument);

    Boolean deleteByDelId(Integer delId, String path);
}

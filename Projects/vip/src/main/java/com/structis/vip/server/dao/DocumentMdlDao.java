package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.dao.support.GenericDao;

public interface DocumentMdlDao extends GenericDao<DocumentMdl, Integer> {

    List<DocumentMdl> getDocumentModelsByLanguage(Integer lgId);

    List<DocumentMdl> getDocumentModelsByLanguageAndType(Integer lgId, String type);

    List<DocumentMdl> getAllDocumentModels();

    List<DomDel> getDocumentsByDelegation(Integer delegationId);

    List<DomDel> getDocumentsByDelegationAndLanguage(Integer delegationId, Integer languageId);

    List<DomDel> getDocumentsByDelegationLanguageAndType(Integer delegationId, Integer languageId, String type);

    List<DocumentMdl> getAllDocumentModelsByEntiteId(String entId);

    DocumentMdl insert(DocumentMdl model);

    DocumentMdl update(DocumentMdl doc);

    List<DocumentMdl> findByName(String name, String entId);
}

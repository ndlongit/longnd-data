package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDocumentModelService extends GenericEntityService<DocumentMdl, Integer> {
	DocumentMdl findById(Integer id);
	List<DocumentMdl> getAllDocumentModels();
	List<DocumentMdl> getDocumentModelsByLanguage(Integer langId);
	List<DocumentMdl> getDocumentModelsByLanguageAndType(Integer langId, String type);
	List<DocumentMdl> getDocumentModelsByEntiteId(String entId);
	List<DomDel> getDocumentsByDelegation(Integer delegationId);
	List<DomDel> getDocumentsByDelegationAndLanguage(Integer delegationId, Integer languageId);
	List<DomDel> getDocumentsByDelegationLanguageAndType(Integer delegationId, Integer languageId, String type);
	Boolean createNewDocument(DomDel document, String path);
	Boolean deleteDocument(DomDel document, String path);
	DocumentMdl insert(DocumentMdl document);
	DocumentMdl update(DocumentMdl doc);
	List<DocumentMdl> findByName(String name, String entId);
}

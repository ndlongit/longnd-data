package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;

@RemoteServiceRelativePath("springGwtServices/clientDocumentModelService")
public interface ClientDocumentMdlService extends RemoteService {
	DocumentMdlModel findById(Integer docId);

	List<DocumentMdlModel> getAllDocumentModels();

	List<DocumentMdlModel> getAllDocumentModelsByEntite(EntiteModel en);

	List<DocumentMdlModel> getDocumentModelsByLanguage(LanguageModel lm);

	List<DocumentMdlModel> getDocumentModelsByLanguageAndType(LanguageModel lm, String type);

	List<DomDelModel> getDocumentsByDelegation(Integer delegationId);

	List<DomDelModel> getDocumentsByDelegationAndLanguage(Integer delegationId, Integer languageId);

	List<DomDelModel> getDocumentsByDelegationLanguageAndType(Integer delegationId, Integer languageId, String type);

	Boolean createNewDocument(DomDelModel document);

	Boolean createNewDocument(List<DomDelModel> documents);
	
	Boolean deleteDocument(DomDelModel document);

	boolean delete(DocumentMdlModel model) throws DocumentMdlException;

	DocumentMdlModel insert(DocumentMdlModel model) throws DocumentMdlException;

	DocumentMdlModel update(DocumentMdlModel model) throws DocumentMdlException;

	Boolean deleteFile(DocumentMdlModel model, boolean isDeleteFile, boolean isDeleteTempFile);
}

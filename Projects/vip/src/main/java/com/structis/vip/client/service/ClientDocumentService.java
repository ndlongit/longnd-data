package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.DocumentModel;

@RemoteServiceRelativePath("springGwtServices/clientDocumentService")
public interface ClientDocumentService extends RemoteService {

    List<DocumentModel> findDocuments();

    List<DocumentModel> findDocumentsByName(String name);

    DocumentModel findById(final Integer id);

    DocumentModel insert(DocumentModel docModel);

    DocumentModel update(DocumentModel docModel);

    Boolean delete(DocumentModel docModel);

    PagingLoadResult<DocumentModel> findDocumentsWithPaging(String name, PagingLoadConfig config);
}

package com.structis.vip.server.service.domain;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.server.bean.domain.Document;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.core.ResultPagination;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.UserException;
import com.structis.vip.shared.model.DocumentModel;

public interface DomDocService extends GenericEntityService<Document, Integer> {

	public List<Document> findDocuments();

	public Document findById(final int id);

	public List<Document> findByName(final String userName);

	public Document insert(Document doc);

	public Document update(Document doc);
	
	public Boolean delete(Integer docId);

	public ResultPagination<Document> loadDocumentPaging(String name, PagingLoadConfig config);

}

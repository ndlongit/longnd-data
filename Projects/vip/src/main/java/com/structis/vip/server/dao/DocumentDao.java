package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Document;
import com.structis.vip.server.dao.support.GenericDao;

public interface DocumentDao extends GenericDao<Document, Integer> {
	List<Document> getAllDocument();
	
	Document insert(Document doc);
	Document update(Document doc);
	
	Boolean delete(Integer docId);
	
	List<Document> findByName(String name);

	List<Document> loadDocumentPaging(String name, int offset, int limit);

	Integer getCount(String name);
}

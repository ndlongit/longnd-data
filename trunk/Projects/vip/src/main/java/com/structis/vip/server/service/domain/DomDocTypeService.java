package com.structis.vip.server.service.domain;

import java.util.List;


import com.structis.vip.server.bean.domain.DocumentType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDocTypeService extends GenericEntityService<DocumentType, Integer> {
	List<DocumentType> getCategories();
	DocumentType findById(Integer catId);
	DocumentType insert(DocumentType doc);
	DocumentType update(DocumentType doc);	
	void delete(DocumentType doc);
}

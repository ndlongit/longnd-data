package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.DocumentType;
import com.structis.vip.server.dao.support.GenericDao;

public interface DocTypeDao extends GenericDao<DocumentType, Integer> {

    DocumentType insert(DocumentType doc);

    DocumentType update(DocumentType doc);

    @Override
    void delete(DocumentType doc);

}

package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DocumentType;
import com.structis.vip.server.dao.DocTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDocTypeService")
public class DomDocTypeServiceImpl extends GenericEntityServiceImpl<DocumentType, Integer> implements DomDocTypeService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomDocTypeServiceImpl.class);

    @Autowired
    @Qualifier("DocTypeDao")
    private DocTypeDao docTypeDao;

    @Override
    public GenericDao<DocumentType, Integer> getDao() {
        return this.docTypeDao;
    }

    @Override
    public List<DocumentType> getCategories() {
        return this.find();
    }

    @Override
    public DocumentType findById(Integer catId) {
        DocumentType l = this.getByPrimaryKey(catId);
        return l;
    }

    @Override
    public DocumentType insert(DocumentType doc) {
        return this.docTypeDao.insert(doc);
    }

    @Override
    public DocumentType update(DocumentType doc) {
        return this.docTypeDao.update(doc);
    }

    @Override
    public DocumentType getNew() {
        return new DocumentType();
    }

    @Override
    public DocumentType getNewWithDefaults() {
        return new DocumentType();
    }

}

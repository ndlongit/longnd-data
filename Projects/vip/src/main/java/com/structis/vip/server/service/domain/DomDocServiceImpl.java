package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.structis.vip.server.bean.domain.Document;
import com.structis.vip.server.core.ResultPagination;
import com.structis.vip.server.dao.DocumentDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDocService")
public class DomDocServiceImpl extends GenericEntityServiceImpl<Document, Integer> implements DomDocService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomDocServiceImpl.class);

    @Autowired
    @Qualifier("documentDao")
    private DocumentDao docDao;

    @Override
    public GenericDao<Document, Integer> getDao() {
        return this.docDao;
    }

    @Override
    public Document getNew() {
        return new Document();
    }

    @Override
    public Document getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public Document findById(final int id) {
        Document domUser = this.getByPrimaryKey(id);
        return domUser;
    }

    @Override
    public List<Document> findByName(final String name) {
        return this.docDao.findByName(name);
    }

    @Override
    public Document insert(Document doc) {
        return this.docDao.insert(doc);
    }

    @Override
    public Document update(Document doc) {
        return this.docDao.update(doc);
    }

    @Override
    public Boolean delete(Integer docId) {
        return this.docDao.delete(docId);
    }

    @Override
    public List<Document> findDocuments() {
        return this.docDao.getAllDocument();
    }

    @Override
    public ResultPagination<Document> loadDocumentPaging(String name, PagingLoadConfig config) {

        ResultPagination<Document> resultPagination = new ResultPagination<Document>();
        /** --------attach roles--------------- **/
        List<Document> docs = this.docDao.loadDocumentPaging(name, config.getOffset(), config.getLimit());

        /** -------------------------------------- **/
        resultPagination.setData(docs);
        resultPagination.setTotalLength(this.docDao.getCount(name));
        resultPagination.setOffset(config.getOffset());
        return resultPagination;
    }
}

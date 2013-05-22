package com.structis.vip.server.service.client;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.structis.vip.client.service.ClientDocumentService;
import com.structis.vip.server.bean.domain.Document;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDocService;
import com.structis.vip.shared.model.DocumentModel;

@Service("clientDocumentService")
public class ClientDocumentImpl extends DependencyInjectionRemoteServiceServlet implements ClientDocumentService {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(ClientDocumentImpl.class);

    @Autowired
    private DomDocService domDocumentService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentModel> findDocuments() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentImpl.this.domDocumentService.find();
            }
        };
        return (List<DocumentModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentModel> findDocumentsByName(final String name) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentImpl.this.domDocumentService.findByName(name);
            }
        };
        return (List<DocumentModel>) this.callManager(callBack);
    }

    @Override
    public DocumentModel findById(Integer docId) {
        Document lg = this.domDocumentService.getByPrimaryKey(docId);
        return (DocumentModel) this.modelBeanMapper.map(lg);
    }

    @Override
    public DocumentModel insert(DocumentModel model) {
        Document doc = (Document) this.modelBeanMapper.map(model);
        doc.setDate(new Date());
        doc = this.domDocumentService.insert(doc);
        return (DocumentModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public DocumentModel update(DocumentModel model) {
        Document doc = (Document) this.modelBeanMapper.map(model);
        doc = this.domDocumentService.update(doc);
        return (DocumentModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public Boolean delete(DocumentModel model) {
        Document dm = (Document) this.modelBeanMapper.map(model);
        this.domDocumentService.delete(dm);
        return true;
    }

    @Override
    public PagingLoadResult<DocumentModel> findDocumentsWithPaging(final String name, final PagingLoadConfig config) {

        ManagerCallBack manager = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentImpl.this.domDocumentService.loadDocumentPaging(name, config);

            }
        };
        return (PagingLoadResult<DocumentModel>) this.callManager(manager);
    }
}

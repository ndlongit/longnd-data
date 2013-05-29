package com.structis.vip.server.service.client;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.structis.vip.client.service.ClientDocumentMdlService;
import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDemDomService;
import com.structis.vip.server.service.domain.DomDocumentModelService;
import com.structis.vip.server.service.domain.DomDomDelService;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;

@Service("clientDocumentModelService")
public class ClientDocumentMdlImpl extends DependencyInjectionRemoteServiceServlet implements ClientDocumentMdlService {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(ClientDocumentMdlImpl.class);

    @Autowired
    private DomDocumentModelService domDocumentService;

    @Autowired
    private DomDemDomService domDemDomService;

    @Autowired
    private DomDomDelService domDomDelService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentMdlModel> getAllDocumentModels() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.find();
            }
        };
        return (List<DocumentMdlModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentMdlModel> getDocumentModelsByLanguage(final LanguageModel lm) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentModelsByLanguage(lm.getId());
            }
        };
        return (List<DocumentMdlModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentMdlModel> getDocumentModelsByLanguageAndType(final LanguageModel lm, final String type) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentModelsByLanguageAndType(lm.getId(), type);
            }
        };
        return (List<DocumentMdlModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DomDelModel> getDocumentsByDelegation(final Integer delegationId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentsByDelegation(delegationId);
            }
        };
        return (List<DomDelModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DomDelModel> getDocumentsByDelegationAndLanguage(final Integer delegationId, final Integer languageId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentsByDelegationAndLanguage(delegationId, languageId);
            }
        };
        return (List<DomDelModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DomDelModel> getDocumentsByDelegationLanguageAndType(final Integer delegationId, final Integer languageId, final String type) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentsByDelegationLanguageAndType(delegationId, languageId, type);
            }
        };
        return (List<DomDelModel>) this.callManager(callBack);
    }

    @Override
    public DocumentMdlModel findById(Integer docId) {
        DocumentMdl lg = this.domDocumentService.getByPrimaryKey(docId);
        return (DocumentMdlModel) this.modelBeanMapper.map(lg);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DocumentMdlModel> getAllDocumentModelsByEntite(final EntiteModel en) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDocumentMdlImpl.this.domDocumentService.getDocumentModelsByEntiteId(en.getEntId());
            }
        };
        return (List<DocumentMdlModel>) this.callManager(callBack);
    }

    @Override
    public Boolean createNewDocument(DomDelModel document) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ServletContext context = attr.getRequest().getSession().getServletContext();
        String pathContext = context.getRealPath(File.separator);

        DomDel dl = (DomDel) this.modelBeanMapper.map(document);
        return this.domDocumentService.createNewDocument(dl, CatalinaPropertiesUtil.getVipDirectory(pathContext));
    }

    @Override
    public Boolean createNewDocument(List<DomDelModel> documents) {
        for (DomDelModel document : documents) {
            this.createNewDocument(document);
        }
        return true;
    }

    @Override
    public Boolean deleteDocument(DomDelModel document) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ServletContext context = attr.getRequest().getSession().getServletContext();
        String pathContext = context.getRealPath(File.separator);

        DomDel dl = (DomDel) this.modelBeanMapper.map(document);
        return this.domDocumentService.deleteDocument(dl, CatalinaPropertiesUtil.getVipDirectory(pathContext));
    }

    @Override
    public boolean delete(DocumentMdlModel model) throws DocumentMdlException {
        List<DemDom> lstDemDoms = this.domDemDomService.findByDocumentModel(model.getId());
        if ((lstDemDoms != null) && (lstDemDoms.size() != 0)) {
            throw new DocumentMdlException(ExceptionType.DOCUMENT_DELETE_EXIST_IN_DELEGATION_MODEL);
        } else {
            List<DomDel> lstDomDels = this.domDomDelService.findByDocumentModel(model.getId());
            if ((lstDomDels != null) && (lstDomDels.size() != 0)) {
                throw new DocumentMdlException(ExceptionType.DOCUMENT_DELETE_EXIST_IN_DELEGATION);
            } else {
                DocumentMdl dm = (DocumentMdl) this.modelBeanMapper.map(model);
                this.domDocumentService.delete(dm);
                this.deleteFile(model, true, true);
                return true;
            }
        }
    }

    @Override
    public Boolean deleteFile(DocumentMdlModel model, boolean isDeleteFile, boolean isDeleteTempFile) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ServletContext context = attr.getRequest().getSession().getServletContext();
        String pathContext = context.getRealPath(File.separator);

        boolean bResult = true;

        File file = null;
        File tempFile = null;
        if (model.getFilename() != null) {
            file = new File(CatalinaPropertiesUtil.getVipDirectory(pathContext) + ServerConstant.TEMPLATE_FILE_PATH + "/" + model.getFilename());
            LOGGER.info("DELETE DOCUMENT MAIN FILE PATH: " + file.getAbsolutePath());
        }

        if (model.getTempFilename() != null) {
            tempFile = new File(CatalinaPropertiesUtil.getVipDirectory(pathContext) + ServerConstant.TEMPLATE_FILE_PATH + "/"
                    + model.getTempFilename());
            LOGGER.info("DELETE DOCUMENT TEMP FILE PATH: " + file.getAbsolutePath());
        }

        if (isDeleteFile) {
            if ((file != null) && (file.exists())) {
                LOGGER.info("MAIN FILE EXISTS");
                if (!file.delete()) {
                    bResult = false;
                    LOGGER.info("MAIN FILE DELETE FALSE");
                } else {
                    LOGGER.info("MAIN FILE DELETE TRUE");
                }
            } else {
                LOGGER.info("MAIN FILE DOES NOT EXISTS");
            }
        }

        if (isDeleteTempFile) {
            if ((tempFile != null) && (tempFile.exists())) {
                LOGGER.info("TEMP FILE EXISTS");
                if (!tempFile.delete()) {
                    bResult = false;
                    LOGGER.info("TEMP FILE DELETE FALSE");
                } else {
                    LOGGER.info("TEMP FILE DELETE TRUE");
                }
            } else {
                LOGGER.info("TEMP FILE DOES NOT EXISTS");
            }
        }

        return bResult;
    }

    @Override
    public DocumentMdlModel insert(DocumentMdlModel model) {
        List<DocumentMdl> cur = this.domDocumentService.findByName(model.getName(), model.getEntite().getEntId());
        if (cur.isEmpty() == false) {
            throw new DocumentMdlException(ExceptionType.DOCUMENT_INSERT_DUPLICATE_NAME);
        }

        String fileName = FilenameUtils.getName(model.getFilename());
        String tempFileName = FilenameUtils.getName(model.getTempFilename());

        model.setFilename(fileName);
        model.setTempFilename(tempFileName);

        DocumentMdl doc = (DocumentMdl) this.modelBeanMapper.map(model);
        doc = this.domDocumentService.insert(doc);
        return (DocumentMdlModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public DocumentMdlModel update(DocumentMdlModel model) {
        List<DocumentMdl> cur = this.domDocumentService.findByName(model.getName(), model.getEntite().getEntId());
        if (cur.isEmpty() == false) {
            if (cur.get(0).getId().intValue() != model.getId().intValue()) {
                throw new DocumentMdlException(ExceptionType.DOCUMENT_INSERT_DUPLICATE_NAME);
            }
        }

        String fileName = FilenameUtils.getName(model.getFilename());
        String tempFileName = FilenameUtils.getName(model.getTempFilename());

        model.setFilename(fileName);
        model.setTempFilename(tempFileName);

        DocumentMdl doc = (DocumentMdl) this.modelBeanMapper.map(model);
        doc = this.domDocumentService.update(doc);
        return (DocumentMdlModel) this.modelBeanMapper.map(doc);
    }
}

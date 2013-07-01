package com.structis.vip.server.service.domain;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.dao.DelegationDao;
import com.structis.vip.server.dao.DocumentMdlDao;
import com.structis.vip.server.dao.DomDelDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDocumentModelService")
public class DomDocumentModelServiceImpl extends GenericEntityServiceImpl<DocumentMdl, Integer> implements DomDocumentModelService {

    private static final Logger LOGGER = Logger.getLogger(DomDocumentModelServiceImpl.class);

    @Autowired
    @Qualifier("documentModelDao")
    private DocumentMdlDao documentMdlDao;

    @Autowired
    @Qualifier("delegationDao")
    private DelegationDao delegationDao;

    @Autowired
    private DomDelegationStatusService domDelegationStatusService;

    @Autowired
    @Qualifier("domDelDao")
    private DomDelDao domDelDao;

    @Override
    public GenericDao<DocumentMdl, Integer> getDao() {
        return this.documentMdlDao;
    }

    @Override
    public DocumentMdl getNew() {
        return new DocumentMdl();
    }

    @Override
    public DocumentMdl getNewWithDefaults() {
        return new DocumentMdl();
    }

    @Override
    public List<DocumentMdl> getAllDocumentModels() {
        return this.documentMdlDao.getAllDocumentModels();
    }

    @Override
    public List<DocumentMdl> getDocumentModelsByLanguage(Integer langId) {
        return this.documentMdlDao.getDocumentModelsByLanguage(langId);
    }

    @Override
    public List<DocumentMdl> getDocumentModelsByLanguageAndType(Integer langId, String type) {
        return this.documentMdlDao.getDocumentModelsByLanguageAndType(langId, type);
    }

    @Override
    public DocumentMdl findById(Integer id) {
        return super.getByPrimaryKey(id);
    }

    @Override
    public List<DomDel> getDocumentsByDelegation(Integer delegationId) {
        return this.documentMdlDao.getDocumentsByDelegation(delegationId);
    }

    @Override
    public List<DomDel> getDocumentsByDelegationAndLanguage(Integer delegationId, Integer languageId) {
        return this.documentMdlDao.getDocumentsByDelegationAndLanguage(delegationId, languageId);
    }

    @Override
    public List<DomDel> getDocumentsByDelegationLanguageAndType(Integer delegationId, Integer languageId, String type) {
        return this.documentMdlDao.getDocumentsByDelegationLanguageAndType(delegationId, languageId, type);
    }

    @Override
    public List<DocumentMdl> getDocumentModelsByEntiteId(String entId) {
        return this.documentMdlDao.getAllDocumentModelsByEntiteId(entId);
    }

    @Override
    public Boolean createNewDocument(DomDel document, String path) {
        File file = new File(path + ServerConstant.SIGNED_DOCUMENT_FILE_PATH + File.separator + document.getSignedFilename());
        if (file.exists()) {
            LOGGER.info(file.getAbsoluteFile() + " EXIST");
            this.domDelDao.save(document);
        } else {
            LOGGER.info(file.getAbsoluteFile() + " DOES NOT EXIST");
        }
        return true;
    }

    @Override
    public Boolean deleteDocument(DomDel document, String path) {
        if (document == null) {
            return false;
        }
        File file = new File(path + ServerConstant.SIGNED_DOCUMENT_FILE_PATH + File.separator + document.getSignedFilename());
        LOGGER.info("DELETE SIGNED DOCUMENT FILE PATH: " + file.getAbsolutePath());
        if (file.exists()) {
            LOGGER.info("SIGNED DOCUMENT EXISTS");
            file.delete();
        }
        this.domDelDao.delete(document);

        Delegation delegation = document.getDelegation();
        if (delegation != null) {
            DelegationStatus status = delegation.getDelegationStatus();
            if (status.getId() == DelegationConstants.DEL_STATUS_VENIR || status.getId() == DelegationConstants.DEL_STATUS_SIGNEE) {

                List<DomDel> signedDocs = getDocumentsByDelegation(delegation.getId());

                status = new DelegationStatus();
                if (signedDocs == null || signedDocs.size() <= 0) {
                    status.setId(DelegationConstants.DEL_STATUS_ESTABLISH);
                    delegation.setIsSigned(0);
                } else {
                    status.setId(DelegationConstants.DEL_STATUS_SIGNEE);
                    delegation.setIsSigned(1);
                }
                delegation.setDelegationStatus(status);

                this.delegationDao.update(delegation);
                Delegation parent = delegation.getParent();
                if (parent != null) {
                    Calendar currentDate = Calendar.getInstance();
                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(parent.getStartDate());

                    currentDate.set(Calendar.HOUR_OF_DAY, 0);
                    currentDate.set(Calendar.MINUTE, 0);
                    currentDate.set(Calendar.SECOND, 0);
                    currentDate.set(Calendar.MILLISECOND, 0);

                    startDate.set(Calendar.HOUR_OF_DAY, 0);
                    startDate.set(Calendar.MINUTE, 0);
                    startDate.set(Calendar.SECOND, 0);
                    startDate.set(Calendar.MILLISECOND, 0);

                    currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
                    startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));

                    int parentStatus;
                    if (currentDate.before(startDate)) {
                        parentStatus = DelegationConstants.DEL_STATUS_VENIR;
                    } else {
                        parentStatus = DelegationConstants.DEL_STATUS_SIGNEE;
                    }
                    parent.setDelegationStatus(this.domDelegationStatusService.findById(parentStatus));
                    this.delegationDao.update(parent);
                }
            }
        }
        return true;
    }

    @Override
    public DocumentMdl insert(DocumentMdl document) {
        return this.documentMdlDao.insert(document);
    }

    @Override
    public DocumentMdl update(DocumentMdl doc) {
        return this.documentMdlDao.update(doc);
    }

    @Override
    public List<DocumentMdl> findByName(String name, String entId) {
        return this.documentMdlDao.findByName(name, entId);
    }

}

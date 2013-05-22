package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.dao.DelegationModelDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDelegationModelService")
public class DomDelegationModelServiceImpl extends GenericEntityServiceImpl<DelegationMdl, Integer> implements DomDelegationModelService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomDelegationModelServiceImpl.class);

    @Autowired
    @Qualifier("delegationModelDao")
    private DelegationModelDao delegationModelDao;

    @Override
    public GenericDao<DelegationMdl, Integer> getDao() {
        return this.delegationModelDao;
    }

    @Override
    public DelegationMdl getNew() {
        return new DelegationMdl();
    }

    @Override
    public DelegationMdl getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, Integer collaboraterType) {
        return this.delegationModelDao.getDocumentModels(langId, ptyId, natureId, collaboraterType);
    }

    @Override
    public Object getDocumentModels(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType) {
        return this.delegationModelDao.getDocumentModels(langId, ptyId, natureId, entId, collaboraterType);
    }

    @Override
    public List<DelegationMdl> getDelegationModels(String entId, Integer natureId) {
        return this.delegationModelDao.getDelegationModels(entId, natureId);
    }

    @Override
    public DelegationMdl update(DelegationMdl dm) {
        return this.delegationModelDao.update(dm);
    }

    @Override
    public DelegationMdl insert(DelegationMdl dm) {
        return this.delegationModelDao.insert(dm);
    }

    @Override
    public void updateBatch(List<DelegationMdl> dms) {
        this.delegationModelDao.updateBatch(dms);
    }

    @Override
    public void insertBatch(List<DelegationMdl> dms) {
        this.delegationModelDao.insertBatch(dms);
    }

    @Override
    public Boolean existOtherDelegationModel(DelegationMdl dm) {
        String perimetreType = (dm.getPerimetreType() == null) ? null : dm.getPerimetreType().getPtyId();
        Integer colType = (dm.getCollaborateurType() == null) ? null : dm.getCollaborateurType().getId();

        return this.delegationModelDao.existOtherDelegationModel(dm.getId(), dm.getEntite().getEntId(), dm.getDelegationNature().getId(),
                perimetreType, colType, dm.getLanguage().getId());
    }

    @Override
    public DelegationMdl findById(Integer id) {
        return this.getByPrimaryKey(id);
    }

    @Override
    public DelegationMdl insert(DelegationMdl dm, Integer group) {
        return this.delegationModelDao.insert(dm, group);
    }

    @Override
    public Integer getLastGroup() {
        return this.delegationModelDao.getLastGroup();
    }

    @Override
    public Boolean deleteByGroup(Integer group) {
        return this.delegationModelDao.deleteByGroup(group);
    }

    @Override
    public List<DelegationMdl> getDelegationModelsByGroup(Integer group) {
        return this.delegationModelDao.getDelegationModelsByGroup(group);
    }

    @Override
    public List<DelegationMdl> getAllDelegationModelsByEntite(String entiteId) {
        return this.delegationModelDao.getDelegationModelsByEntite(entiteId);
    }

    @Override
    public Integer getGroup(Integer langId, String ptyId, Integer natureId, Integer collaboraterType) {
        return this.delegationModelDao.getGroup(langId, ptyId, natureId, collaboraterType);
    }

    @Override
    public Integer getGroup(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType) {
        return this.delegationModelDao.getGroup(langId, ptyId, natureId, entId, collaboraterType);
    }

    @Override
    public List<DelegationMdl> findByLanguageId(Integer languageId) {
        return this.delegationModelDao.findByLanguageId(languageId);
    }

    @Override
    public List<DelegationMdl> findByNatureId(Integer natureId) {
        return this.delegationModelDao.findByNatureId(natureId);
    }

    @Override
    public Boolean getHasMutiDelegataire(Integer group) {
        return this.delegationModelDao.getHasMutiDelegataire(group);
    }
}

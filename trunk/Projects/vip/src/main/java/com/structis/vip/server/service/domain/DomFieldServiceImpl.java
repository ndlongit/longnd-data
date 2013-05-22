package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.dao.FieldDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domFieldService")
public class DomFieldServiceImpl extends GenericEntityServiceImpl<FieField, Integer> implements DomFieldService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomFieldServiceImpl.class);

    @Autowired
    @Qualifier("fieldDao")
    private FieldDao fieldDao;

    @Override
    public GenericDao<FieField, Integer> getDao() {
        return this.fieldDao;
    }

    @Override
    public FieField getNew() {
        return new FieField();
    }

    @Override
    public FieField getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<FieField> getFieldsByEntiteId(String entiteId) {
        return this.fieldDao.getRulesByEntiteId(entiteId);
    }

    @Override
    public FieField insert(FieField fieField) {
        this.save(fieField);
        return fieField;
    }

    @Override
    public List<FieField> getFieldsByGroupName(String entId, String groupName) {
        return this.fieldDao.getFieldsByGroupName(entId, groupName);
    }

    @Override
    public List<FieField> findByLanguageId(Integer languageId) {
        return this.fieldDao.findByLanguageId(languageId);
    }

    @Override
    public List<FieField> findByEntite(String entId) {
        return this.fieldDao.findByEntite(entId);
    }
}

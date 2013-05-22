package com.structis.vip.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.dao.DemDomDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDemDomService")
public class DomDemDomServiceImpl extends GenericEntityServiceImpl<DemDom, Integer> implements DomDemDomService {

    @Autowired
    @Qualifier("demDomDao")
    private DemDomDao demDomDao;

    @Override
    public GenericDao<DemDom, Integer> getDao() {
        return this.demDomDao;
    }

    @Override
    public DemDom getNew() {
        return null;
    }

    @Override
    public DemDom getNewWithDefaults() {
        return null;
    }

    @Override
    public List<DemDom> getAllDemDomsByDemGroup(Integer group) {
        return this.demDomDao.getAllDemDomsByDemGroup(group);
    }

    @Override
    public DemDom insert(DemDom demDom) {
        DemDom createDemDom = this.demDomDao.insert(demDom);
        return createDemDom;
    }

    @Override
    public Boolean deleteByGroup(Integer group) {
        return this.demDomDao.deleteByGroup(group);
    }

    @Override
    public List<DemDom> findByDocumentModel(Integer dId) {
        return this.demDomDao.findByDocumentModel(dId);
    }
}

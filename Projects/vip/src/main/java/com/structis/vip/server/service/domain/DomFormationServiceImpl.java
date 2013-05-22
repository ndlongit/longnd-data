package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.dao.FormationDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domFormationService")
public class DomFormationServiceImpl extends GenericEntityServiceImpl<Formation, Integer> implements DomFormationService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomFormationServiceImpl.class);

    @Autowired
    @Qualifier("formationDao")
    private FormationDao formationDao;

    @Override
    public GenericDao<Formation, Integer> getDao() {
        return this.formationDao;
    }

    @Override
    public Formation getNew() {
        return new Formation();
    }

    @Override
    public Formation getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<Formation> findByEntite(String entiteId) {
        return this.formationDao.findByEntite(entiteId);
    }

    @Override
    public Formation insert(Formation nature) {
        return this.formationDao.insert(nature);
    }

    @Override
    public Formation update(Formation nature) {
        return this.formationDao.update(nature);
    }

    @Override
    public List<Formation> findAll() {
        return this.find();
    }
}

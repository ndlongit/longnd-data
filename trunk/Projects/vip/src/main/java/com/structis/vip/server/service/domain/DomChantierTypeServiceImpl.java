package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.dao.ChantierTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domChantierTypeService")
public class DomChantierTypeServiceImpl extends GenericEntityServiceImpl<ChantierType, Integer> implements DomChantierTypeService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomChantierTypeServiceImpl.class);

    @Autowired
    @Qualifier("chantierTypeDao")
    private ChantierTypeDao chantierTypeDao;

    @Override
    public GenericDao<ChantierType, Integer> getDao() {
        return this.chantierTypeDao;
    }

    @Override
    public ChantierType getNew() {
        return new ChantierType();
    }

    @Override
    public ChantierType getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<ChantierType> findChantierByEntite(String entiteId) {
        return this.chantierTypeDao.findChantierByEntite(entiteId);
    }

    @Override
    public ChantierType insert(ChantierType nature) {
        return this.chantierTypeDao.insert(nature);
    }

    @Override
    public ChantierType update(ChantierType nature) {
        return this.chantierTypeDao.update(nature);
    }

    @Override
    public List<ChantierType> findAll() {
        return this.find();
    }
}

package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.dao.EntiteJuridiqueDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domEntiteJuridiqueService")
public class DomEntiteJuridiqueServiceImpl extends GenericEntityServiceImpl<EntiteJuridique, Integer> implements DomEntiteJuridiqueService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomEntiteJuridiqueServiceImpl.class);

    @Autowired
    @Qualifier("entiteJuridiqueDao")
    private EntiteJuridiqueDao entiteJuriDao;

    @Override
    public GenericDao<EntiteJuridique, Integer> getDao() {
        return this.entiteJuriDao;
    }

    @Override
    public EntiteJuridique getNew() {
        return new EntiteJuridique();
    }

    @Override
    public EntiteJuridique getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public EntiteJuridique insert(EntiteJuridique doc) {
        return this.entiteJuriDao.insert(doc);
    }

    @Override
    public EntiteJuridique update(EntiteJuridique doc) {
        return this.entiteJuriDao.update(doc);
    }

    @Override
    public List<EntiteJuridique> findByEntiteId(String entId) {
        return this.entiteJuriDao.findByEntiteId(entId);
    }

    @Override
    public EntiteJuridique getDefaultByEntiteId(String entityId) {
        return this.entiteJuriDao.getDefaultByEntiteId(entityId);
    }

    @Override
    public EntiteJuridique findById(Integer id) {
        return this.getByPrimaryKey(id);
    }
}

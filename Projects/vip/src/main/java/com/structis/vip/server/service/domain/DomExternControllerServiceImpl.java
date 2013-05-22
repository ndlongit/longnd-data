package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.dao.ExternControllerDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domExternControllerService")
public class DomExternControllerServiceImpl extends GenericEntityServiceImpl<ExternController, Integer> implements DomExternControllerService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomExternControllerServiceImpl.class);

    @Autowired
    @Qualifier("externControllerDao")
    private ExternControllerDao controlerDao;

    @Override
    public GenericDao<ExternController, Integer> getDao() {
        return this.controlerDao;
    }

    @Override
    public ExternController getNew() {
        return new ExternController();
    }

    @Override
    public ExternController getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public ExternController insert(ExternController nature) {
        return this.controlerDao.insert(nature);
    }

    @Override
    public ExternController update(ExternController nature) {
        return this.controlerDao.update(nature);
    }

    @Override
    public List<ExternController> findAll() {
        return this.find();
    }

    @Override
    public List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId) {
        return this.controlerDao.getDelegatairesKeyValueByEntiteId(entiteId);
    }

}

package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegationType;
import com.structis.vip.server.dao.DelegationTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDelegationTypeService")
public class DomDelegationTypeServiceImpl extends GenericEntityServiceImpl<DelegationType, Integer> implements DomDelegationTypeService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomDelegationTypeServiceImpl.class);

    @Autowired
    @Qualifier("delegationTypeDao")
    private DelegationTypeDao delegationTypeDao;

    @Override
    public GenericDao<DelegationType, Integer> getDao() {
        return this.delegationTypeDao;
    }

    @Override
    public DelegationType getNew() {
        return new DelegationType();
    }

    @Override
    public DelegationType getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<DelegationType> getAllTypes() {
        return this.find();
    }

    @Override
    public DelegationType getByType(String type) {
        return this.delegationTypeDao.getByType(type);
    }

    @Override
    public DelegationType getById(Integer id) {
        return this.delegationTypeDao.getById(id);
    }

    @Override
    public DelegationType insert(DelegationType delegationType) {
        return this.delegationTypeDao.insert(delegationType);
    }

    @Override
    public DelegationType update(DelegationType delegationType) {
        return this.delegationTypeDao.update(delegationType);
    }
}

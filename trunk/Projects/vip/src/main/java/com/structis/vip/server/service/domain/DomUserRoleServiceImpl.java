package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.dao.UserRoleDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domUserRoleService")
public class DomUserRoleServiceImpl extends GenericEntityServiceImpl<UserRole, Integer> implements DomUserRoleService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomUserRoleServiceImpl.class);

    @Autowired
    @Qualifier("userRoleDao")
    private UserRoleDao userRoleDao;

    @Override
    public GenericDao<UserRole, Integer> getDao() {
        return this.userRoleDao;
    }

    @Override
    public UserRole getNew() {
        return new UserRole();
    }

    @Override
    public UserRole getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<UserRole> findByPerimetre(String perimetreId) {
        return this.userRoleDao.findByPerimetre(perimetreId);
    }
}

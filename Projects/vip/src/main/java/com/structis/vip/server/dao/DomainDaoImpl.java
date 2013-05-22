package com.structis.vip.server.dao;

import org.springframework.stereotype.Repository;

import com.structis.vip.server.bean.domain.Domain;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("domainDao")
public class DomainDaoImpl extends HibernateGenericDao<Domain, Integer> implements DomainDao {

    public DomainDaoImpl() {
        super(Domain.class);
    }
}

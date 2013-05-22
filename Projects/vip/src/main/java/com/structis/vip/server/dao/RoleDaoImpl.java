package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.structis.vip.server.bean.domain.Role;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("roleDao")
public class RoleDaoImpl extends HibernateGenericDao<Role, Integer> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRoles() {
        String sql = " from Role r ";

        Query query = this.getEntityManager().createQuery(sql);
        List<Role> resultList = query.getResultList();
        return resultList;
    }

}

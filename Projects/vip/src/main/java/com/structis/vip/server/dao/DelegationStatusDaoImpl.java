package com.structis.vip.server.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("delegationStatusDao")
public class DelegationStatusDaoImpl extends HibernateGenericDao<DelegationStatus, Integer> implements DelegationStatusDao {

    public DelegationStatusDaoImpl() {
        super(DelegationStatus.class);
    }

    @Override
    @Transactional
    public DelegationStatus insert(DelegationStatus status) {
        this.save(status);
        return status;
    }

    @Override
    @Transactional
    public DelegationStatus update(DelegationStatus dl) {
        EntityManager em = this.getEntityManager();
        try {
            DelegationStatus jpa = this.get(dl);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(dl, jpa);
                em.merge(jpa);
                return jpa;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
        return null;
    }
}

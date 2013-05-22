package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("delegationDelegataireDao")
public class DelegationDelegataireDaoImpl extends HibernateGenericDao<DelegationDelegataire, Integer> implements DelegationDelegataireDao {

    public DelegationDelegataireDaoImpl() {
        super(DelegationDelegataire.class);
    }

    @Override
    @Transactional
    public DelegationDelegataire update(DelegationDelegataire dl) {
        EntityManager em = this.getEntityManager();
        try {
            DelegationDelegataire jpa = this.get(dl);
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

    @Override
    @Transactional
    public DelegationDelegataire insert(DelegationDelegataire entity) {
        System.out.println("****delegation = ");
        this.save(entity);
        return entity;
    }

    @Override
    public void deleteByDelegation(Integer delId) {
        String sql = " delete from DelegationDelegataire d where d.delId = :delId";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("delId", delId);

        query.executeUpdate();

    }

    @Override
    public List<Collaborateur> findDelegatairesByDelegation(int delId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct c from Collaborateur c where c.id in (select d.colId from DelegationDelegataire d where d.delId = :delId )");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delId", delId);
        List<Collaborateur> result = query.getResultList();
        return result;

    }

}

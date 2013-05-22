package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("demDomDao")
public class DemDomDaoImpl extends HibernateGenericDao<DemDom, Integer> implements DemDomDao {

    public DemDomDaoImpl() {
        super(DemDom.class);
    }

    @Override
    public List<DemDom> getAllDemDomsByDemGroup(Integer group) {
        String sql = " from DemDom d where d.group = :group ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("group", group);
        @SuppressWarnings("unchecked")
        List<DemDom> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public DemDom insert(DemDom domDem) {
        this.save(domDem);
        return domDem;
    }

    @Override
    @Transactional
    public Boolean deleteByGroup(Integer group) {
        if ((group != null) && (group != 0)) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from DemDom d where d.group = :group");

            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("group", group);

            query.executeUpdate();
        }
        return true;
    }

    @Override
    public List<DemDom> findByDocumentModel(Integer dId) {
        String sql = " from DemDom d where d.documentMdl.id = :dId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("dId", dId);
        @SuppressWarnings("unchecked")
        List<DemDom> resultList = query.getResultList();
        return resultList;
    }
}

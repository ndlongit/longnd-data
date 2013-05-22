package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Document;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("documentDao")
public class DocumentDaoImpl extends HibernateGenericDao<Document, Integer> implements DocumentDao {

    public DocumentDaoImpl() {
        super(Document.class);
    }

    @Override
    public List<Document> getAllDocument() {
        String sql = " from Document d ";
        Query query = this.getEntityManager().createQuery(sql);
        @SuppressWarnings("unchecked")
        List<Document> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public Document insert(Document domDem) {
        this.save(domDem);
        return domDem;
    }

    @Override
    @Transactional
    public Boolean delete(Integer docId) {
        if ((docId != null) && (docId != 0)) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from Document d where d.id = :id");

            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("id", docId);

            query.executeUpdate();
        }
        return true;
    }

    @Override
    public List<Document> findByName(String name) {
        String sql = " from Document d where d.name like :name ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("name", "%" + name + "%");
        @SuppressWarnings("unchecked")
        List<Document> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public Document update(Document doc) {
        EntityManager em = this.getEntityManager();
        try {
            Document jpa = this.get(doc);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(doc, jpa);
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
    public List<Document> loadDocumentPaging(String name, int offset, int limit) {
        String sql = " from Document d where d.name like :name ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("name", "%" + name + "%");
        if (offset == 0)
            query.setMaxResults(limit);
        else
            query.setMaxResults(limit * offset);
        List<Document> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Integer getCount(String name) {
        String sql = " select count(*) from Document d where d.name like :name ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("name", "%" + name + "%");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}

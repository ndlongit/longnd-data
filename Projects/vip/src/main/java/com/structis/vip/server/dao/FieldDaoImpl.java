package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("fieldDao")
public class FieldDaoImpl extends HibernateGenericDao<FieField, Integer> implements FieldDao {

    public FieldDaoImpl() {
        super(FieField.class);
    }

    @Override
    public List<FieField> getRulesByEntiteId(String entId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from FieField f where f.entite.id = :entId order by f.order");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("entId", entId);

        List<FieField> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public FieField insert(FieField fieField) {
        this.save(fieField);
        return fieField;
    }

    @Override
    public List<FieField> getFieldsByGroupName(String entId, String groupName) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from FieField f where f.entite.id = :entId and f.group = :groupName order by f.order");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("entId", entId);
        query.setParameter("groupName", groupName);

        List<FieField> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<FieField> findByLanguageId(Integer languageId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from FieField f where f.language.id = :languageId order by f.order");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("languageId", languageId);

        List<FieField> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<FieField> findByEntite(String entId) {
        String sql = "from FieField f where f.entite.entId = :entId";
        Query query = this.createQuery(sql);
        query.setParameter("entId", entId);

        return this.getResultList(query);
    }
}

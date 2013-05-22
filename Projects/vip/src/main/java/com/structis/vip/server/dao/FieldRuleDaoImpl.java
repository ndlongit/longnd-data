package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("fieldRuleDao")
public class FieldRuleDaoImpl extends HibernateGenericDao<FieldRule, Integer> implements FieldRuleDao {

    public FieldRuleDaoImpl() {
        super(FieldRule.class);
    }

    @Override
    public List<FieldRule> getRulesByDemGroup(Integer group) {
        String sql = " from FieldRule p where p.group = :group order by p.field.order";

        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("group", group);

        List<FieldRule> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<FieldRule> getRules(String entId, String ptyId, Integer dnaId, Integer lagId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select p from FieldRule p, DelegationMdl dm where p.demId = dm.id and ( dm.perimetreType is null or dm.perimetreType.id = :ptyId)");
        sql.append(" and (dm.language.id = :lagId) and (dm.entite.id = :entId and p.field.entite.id= :entId) and dm.delegationNature.id = :dnaId and (p.isDisplayed = 1)");

        Query query = this.getEntityManager().createQuery(sql.toString());

        query.setParameter("entId", entId);
        query.setParameter("ptyId", ptyId);
        query.setParameter("dnaId", dnaId);
        query.setParameter("lagId", lagId);

        List<FieldRule> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public FieldRule update(FieldRule dl) {
        EntityManager em = this.getEntityManager();
        try {
            FieldRule jpa = this.get(dl);
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
    public FieldRule insert(FieldRule fieldRule) {
        this.save(fieldRule);
        return fieldRule;
    }

    @Override
    @Transactional
    public Boolean deleteByGroup(Integer group) {
        if ((group != null) && (group != 0)) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from FieldRule f where f.group = :group");

            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("group", group);

            query.executeUpdate();
        }
        return true;
    }
}

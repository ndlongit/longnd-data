package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("entiteDao")
public class EntiteDaoImpl extends HibernateGenericDao<Entite, String> implements EntiteDao {

    public EntiteDaoImpl() {
        super(Entite.class);
    }

    @Override
    @Transactional
    public Boolean update(Entite en) {
        EntityManager em = this.getEntityManager();
        try {
            Entite jpa = this.get(en);
            if (jpa != null && jpa.getEntId() != null) {
                DataCopier.copyNotIdFields(em, jpa);
                // jpa.setDefaultLang(en.getDefaultLang());
                // jpa.setName(en.getName());
                em.merge(jpa);
            }
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public List<Entite> findByLanguageId(Integer languageId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from Entite e where e.language.id = :languageId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("languageId", languageId);

        return query.getResultList();
    }

}

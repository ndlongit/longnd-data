package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("externControllerDao")
public class ExternControllerDaoImpl extends HibernateGenericDao<ExternController, Integer> implements ExternControllerDao {

    @Autowired
    PerimetreDao perimetreDao;

    public ExternControllerDaoImpl() {
        super(ExternController.class);
    }

    @Override
    @Transactional
    public ExternController insert(ExternController nature) {
        this.save(nature);
        return nature;
    }

    @Override
    @Transactional
    public ExternController update(ExternController dl) {
        EntityManager em = this.getEntityManager();
        try {
            ExternController jpa = this.get(dl);
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

    @SuppressWarnings("unchecked")
    @Override
    public List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId) {
        StringBuffer sb = new StringBuffer();

        sb.append(" select new com.structis.vip.server.bean.domain.core.business.KeyValueVM(ec.name, ec.name,'1') from ExternController ec ").append(
                " order by ec.name");

        Query query = this.getEntityManager().createQuery(sb.toString());

        List<KeyValueVM> resultList = query.getResultList();

        return resultList;
    }

}

package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.CollaborateurFormation;
import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("collaborateurFormationDao")
public class CollaborateurFormationDaoImpl extends HibernateGenericDao<CollaborateurFormation, Integer> implements CollaborateurFormationDao {

    public CollaborateurFormationDaoImpl() {
        super(CollaborateurFormation.class);
    }

    @Override
    public List<CollaborateurFormation> findByCollaborateurId(Integer id) {
        String sql = " from CollaborateurFormation d where d.collaborateur.id = :id ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("id", id);
        List<CollaborateurFormation> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public CollaborateurFormation insert(CollaborateurFormation model) {
        this.save(model);
        return model;
    }

    @Override
    @Transactional
    public CollaborateurFormation update(CollaborateurFormation dl) {
        EntityManager em = this.getEntityManager();
        try {
            CollaborateurFormation jpa = this.get(dl);
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
    public Boolean deleteByCollaborateurId(Integer id) {
        if ((id != null) && (id != 0)) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from CollaborateurFormation d where d.collaborateur.id = :id");

            Query query = this.getEntityManager().createQuery(sql.toString());
            query.setParameter("id", id);

            query.executeUpdate();
        }
        return true;
    }

    @Override
    @Transactional
    public List<Formation> insertList(Collaborateur collaborateur, List<Formation> list) {
        List<Formation> ret = new ArrayList<Formation>();
        List<CollaborateurFormation> currentItems = this.findByCollaborateurId(collaborateur.getId());

        if (list != null && list.isEmpty() == false) {
            if (currentItems.isEmpty() == false) {
                for (CollaborateurFormation item : currentItems) {
                    boolean has = false;
                    for (Formation formation : list) {
                        if (formation.getId().intValue() == item.getFormation().getId().intValue()
                                && collaborateur.getId().intValue() == item.getCollaborateur().getId().intValue()) {
                            has = true;
                            break;
                        }
                    }
                    if (has == false) {
                        this.delete(item);
                    }
                }
            }

            CollaborateurFormation entity = null;
            for (Formation item : list) {
                entity = this.findByColIdAndForId(collaborateur.getId(), item.getId());
                if (entity != null) {
                    entity.setDate(item.getDate());
                    entity = this.update(entity);
                } else {
                    entity = new CollaborateurFormation();
                    entity.setCollaborateur(collaborateur);
                    entity.setFormation(item);
                    entity.setDate(item.getDate());

                    entity = this.insert(entity);
                }

                if (entity != null) {
                    ret.add(item);
                }
            }
        } else {
            if (currentItems.isEmpty() == false) {
                this.delete(currentItems);
            }
        }

        return ret;
    }

    @Override
    public CollaborateurFormation findByColIdAndForId(Integer colId, Integer forId) {
        String sql = " from CollaborateurFormation d where d.collaborateur.id = :colId and d.formation.id = :forId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("colId", colId);
        query.setParameter("forId", forId);
        List<CollaborateurFormation> resultList = query.getResultList();
        if (resultList.isEmpty() == false) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<CollaborateurFormation> findByFormationId(Integer forId) {
        String sql = " from CollaborateurFormation d where d.formation.id = :forId ";
        Query query = this.getEntityManager().createQuery(sql);
        query.setParameter("forId", forId);
        List<CollaborateurFormation> resultList = query.getResultList();
        return resultList;
    }
}

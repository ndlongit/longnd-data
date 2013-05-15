package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.bean.domain.CollaborateurType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("collaborateurTypeDao")
public class CollaborateurTypeDaoImpl extends HibernateGenericDao<CollaborateurType, Integer> implements CollaborateurTypeDao {
	public CollaborateurTypeDaoImpl() {
		super(CollaborateurType.class);
	}
	
	@Override
	public List<CollaborateurType> getAllCollaborateurs() {
		StringBuffer sb = new StringBuffer();
		sb.append(" from CollaborateurType c");
		Query query = getEntityManager().createQuery(sb.toString());		
		@SuppressWarnings("unchecked")
		List<CollaborateurType> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<CollaborateurType> getCollaborateurTypeByEntite(String entiteId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from CollaborateurType c where c.entite.id = :idEntite");
		Query query = getEntityManager().createQuery(sb.toString());		
		query.setParameter("idEntite", entiteId);
		@SuppressWarnings("unchecked")
		List<CollaborateurType> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@Transactional
	public CollaborateurType insert(CollaborateurType doc) {
		this.save(doc);
		return doc;
	}

	@Override
	@Transactional
	public CollaborateurType update(CollaborateurType doc) {
		EntityManager em = getEntityManager();
		try {					
			CollaborateurType jpa = get(doc);		
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
}

package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("chantierTypeDao")
public class ChantierTypeDaoImpl extends HibernateGenericDao<ChantierType, Integer> implements ChantierTypeDao {

	public ChantierTypeDaoImpl() {
		super(ChantierType.class);
	}

	@Override
	public List<ChantierType> findChantierByEntite(String entiteId) {
		String sql = " from ChantierType p where  p.entite.id = :idEntite order by p.label ";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idEntite", entiteId);		
		List<ChantierType> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public ChantierType insert(ChantierType nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public ChantierType update(ChantierType dl) {
		EntityManager em = getEntityManager();
		try {					
			ChantierType jpa = get(dl);		
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

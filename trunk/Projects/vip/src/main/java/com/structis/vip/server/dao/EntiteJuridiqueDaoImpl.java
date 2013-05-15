package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("entiteJuridiqueDao")
public class EntiteJuridiqueDaoImpl extends HibernateGenericDao<EntiteJuridique, Integer> implements EntiteJuridiqueDao {

	public EntiteJuridiqueDaoImpl() {
		super(EntiteJuridique.class);
	}

	@Transactional
	public EntiteJuridique insert(EntiteJuridique entiteJuridique) {
		this.save(entiteJuridique);		
		return entiteJuridique; 
	}

	@Transactional
	public EntiteJuridique update(EntiteJuridique dl) {
		EntityManager em = getEntityManager();
		try {					
			EntiteJuridique jpa = get(dl);		
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
	public List<EntiteJuridique> findByEntiteId(String entId) {
		String sql = " from EntiteJuridique e where e.entite.entId = :entId order by e.name ";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("entId", entId);		
		List<EntiteJuridique> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public EntiteJuridique getDefaultByEntiteId(String entityId) {
		String sql = " from EntiteJuridique e where e.entite.entId = :entityId and e.isDefault = 1 ";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("entityId", entityId);
		List<EntiteJuridique> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}

package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("formationDao")
public class FormationDaoImpl extends HibernateGenericDao<Formation, Integer> implements FormationDao {

	public FormationDaoImpl() {
		super(Formation.class);
	}

	@Override
	public List<Formation> findByEntite(String entiteId) {
		String sql = " from Formation p where  p.entite.id = :idEntite order by p.label ";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idEntite", entiteId);		
		List<Formation> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public Formation insert(Formation nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public Formation update(Formation dl) {
		EntityManager em = getEntityManager();
		try {					
			Formation jpa = get(dl);		
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

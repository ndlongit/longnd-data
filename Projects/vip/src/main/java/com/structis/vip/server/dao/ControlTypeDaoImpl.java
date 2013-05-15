package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.ControlType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("controlTypeDao")
public class ControlTypeDaoImpl extends HibernateGenericDao<ControlType, Integer> implements ControlTypeDao {

	public ControlTypeDaoImpl() {
		super(ControlType.class);
	}

	@Override
	public List<ControlType> findByEntite(String entiteId) {
		String sql = " from ControlType p where  p.entite.id = :idEntite order by p.label ";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idEntite", entiteId);		
		List<ControlType> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public ControlType insert(ControlType nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public ControlType update(ControlType dl) {
		EntityManager em = getEntityManager();
		try {					
			ControlType jpa = get(dl);		
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

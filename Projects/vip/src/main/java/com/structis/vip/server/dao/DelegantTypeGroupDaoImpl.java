package com.structis.vip.server.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegantTypeGroup;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("delegantTypeGroupDao")
public class DelegantTypeGroupDaoImpl extends HibernateGenericDao<DelegantTypeGroup, Integer> implements DelegantTypeGroupDao {

	public DelegantTypeGroupDaoImpl() {
		super(DelegantTypeGroup.class);
	}

	
	@Transactional
	public DelegantTypeGroup update(DelegantTypeGroup dl) {
		EntityManager em = getEntityManager();
		try {					
			DelegantTypeGroup jpa = get(dl);		
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


	@Transactional
	public DelegantTypeGroup insert(DelegantTypeGroup entity) {
		System.out.println("****delegation = " );
		this.save(entity);		
		return entity; 
	}
}

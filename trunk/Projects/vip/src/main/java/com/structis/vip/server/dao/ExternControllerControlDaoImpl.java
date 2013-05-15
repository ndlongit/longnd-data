package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("externControllerControlDao")
public class ExternControllerControlDaoImpl extends HibernateGenericDao<ExtControllerControl, Integer> implements ExternControllerControlDao {

	@Autowired PerimetreDao perimetreDao;
	
	public ExternControllerControlDaoImpl() {
		super(ExtControllerControl.class);
	}

	@Transactional
	public ExtControllerControl insert(ExtControllerControl nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public ExtControllerControl update(ExtControllerControl dl) {
		EntityManager em = getEntityManager();
		try {					
			ExtControllerControl jpa = get(dl);		
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
	public List<ExtControllerControl> findByControl(Integer id) {
		String sql = " from ExtControllerControl c where c.control.id = :id";		
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", id);
		
		List<ExtControllerControl> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public Boolean deleteByControl(Integer control) {
		String sql = " delete from ExtControllerControl c where c.control.id = :id";		
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("id", control);
		
		int row = query.executeUpdate();
		return (row > 0);
	}

	
}

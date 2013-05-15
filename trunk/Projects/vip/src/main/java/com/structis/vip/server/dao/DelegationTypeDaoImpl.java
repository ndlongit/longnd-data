package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegationType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("delegationTypeDao")
public class DelegationTypeDaoImpl extends HibernateGenericDao<DelegationType, Integer> implements DelegationTypeDao {

	public DelegationTypeDaoImpl() {
		super(DelegationType.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DelegationType getByType(String type) {
		String sql = " from DelegationType d " + " where LOWER(d.name) = :type " + " order by d.name";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("type", type);
		List<DelegationType> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public DelegationType getById(Integer id) {
		String sql = " from DelegationType d " + " where d.id = :typeId";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("typeId", id);
		List<DelegationType> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public DelegationType insert(DelegationType delegationType) {
		this.save(delegationType);
		return delegationType;
	}

	@Transactional
	public DelegationType update(DelegationType delegationType) {
		EntityManager em = getEntityManager();
		try {
			DelegationType jpa = get(delegationType);
			if (jpa != null && jpa.getId() != null) {
				DataCopier.copyNotIdFields(delegationType, jpa);
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

package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("payCodeDao")
public class PayCodeDaoImpl extends HibernateGenericDao<PayCode, Integer> implements PayCodeDao {

	public PayCodeDaoImpl() {
		super(PayCode.class);
	}

	@Override
	public PayCode findByCode(String code) {
		String sql = " from PayCode p where p.code = :code";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("code", code);		
		List<PayCode> resultList = query.getResultList();
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

	@Transactional
	public PayCode insert(PayCode nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public PayCode update(PayCode dl) {
		EntityManager em = getEntityManager();
		try {					
			PayCode jpa = get(dl);		
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

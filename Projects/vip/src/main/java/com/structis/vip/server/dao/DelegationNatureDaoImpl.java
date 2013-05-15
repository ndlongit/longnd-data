package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegationNature;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.model.DelegationNatureModel;

@Repository("delegationNatureDao")
public class DelegationNatureDaoImpl extends HibernateGenericDao<DelegationNature, Integer> implements DelegationNatureDao {

	public DelegationNatureDaoImpl() {
		super(DelegationNature.class);
	}

	@Override
	public List<DelegationNatureModel> findNatureByEntite(String entiteId) {
		String sql = " select p from DelegationNature p where  p.entite.id = :idEntite order by p.name ";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idEntite", entiteId);		
		@SuppressWarnings("unchecked")
		List<DelegationNatureModel> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public DelegationNature insert(DelegationNature nature) {
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public DelegationNature update(DelegationNature dl) {
		EntityManager em = getEntityManager();
		try {					
			DelegationNature jpa = get(dl);		
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
	public List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(
			String entiteId, String ptyId, Boolean isSub) {		
		StringBuffer sql = new StringBuffer();
		sql.append(" select d.delegationNature from DelegationMdl d join d.delegationNature n where  d.entite.id = :idEntite and d.perimetreType.id = :ptyId");
		if (isSub) {
			sql.append(" and d.subDelegation = 1 ");
		} else {
			sql.append(" and (d.subDelegation is null or d.subDelegation = 0)");
		}
		sql.append(" order by n.name ");
		
		
		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("idEntite", entiteId);		
		query.setParameter("ptyId", ptyId);
		@SuppressWarnings("unchecked")
		List<DelegationNatureModel> resultList = query.getResultList();		
		return resultList;
	}
}

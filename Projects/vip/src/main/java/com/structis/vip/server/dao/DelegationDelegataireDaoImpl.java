package com.structis.vip.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationDelegataire;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

@Repository("delegationDelegataireDao")
public class DelegationDelegataireDaoImpl extends HibernateGenericDao<DelegationDelegataire, Integer> implements DelegationDelegataireDao {

	public DelegationDelegataireDaoImpl() {
		super(DelegationDelegataire.class);
	}

	
	@Transactional
	public DelegationDelegataire update(DelegationDelegataire dl) {
		EntityManager em = getEntityManager();
		try {					
			DelegationDelegataire jpa = get(dl);		
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
	public DelegationDelegataire insert(DelegationDelegataire entity) {
		System.out.println("****delegation = " );
		this.save(entity);		
		return entity; 
	}


	@Override
	public void deleteByDelegation(Integer delId) {
		String sql = " delete from DelegationDelegataire d where d.delId = :delId";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("delId", delId);		
		
		query.executeUpdate();		
		
	}


	@Override
	public List<Collaborateur> findDelegatairesByDelegation(int delId) {
		StringBuffer sql =  new StringBuffer();
		sql.append(" select distinct c from Collaborateur c where c.id in (select d.colId from DelegationDelegataire d where d.delId = :delId )");

		Query query = getEntityManager().createQuery(sql.toString());
		query.setParameter("delId", delId);		
		List<Collaborateur> result = query.getResultList();
		return result;
		
	}	
			
}

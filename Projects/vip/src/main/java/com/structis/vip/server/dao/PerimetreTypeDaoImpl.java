package com.structis.vip.server.dao;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("perimetreTypeDao")
public class PerimetreTypeDaoImpl extends HibernateGenericDao<PerimetreType, String> implements PerimetreTypeDao {

	public PerimetreTypeDaoImpl() {
		super(PerimetreType.class);
	}

	@Override
	public List<PerimetreType> getPerimetreTypes(String entiteId) {
		String sql = " from PerimetreType p " +
		" where p.entite.entId = :idEntite " +
		" order by p.name";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("idEntite", entiteId);
		List<PerimetreType> resultList = query.getResultList();		
		return resultList;
	}

	@Transactional
	public PerimetreType insert(PerimetreType nature) {
		String sql = " select max(p.ptyId) from PerimetreType p ";
		Query query = getEntityManager().createQuery(sql);
		String maxid = (String)query.getSingleResult();
		Integer id = Integer.valueOf(maxid);
		
		DecimalFormat df = new DecimalFormat("0000000000");
		nature.setPtyId(df.format(id+1));
		this.save(nature);		
		return nature; 
	}

	@Transactional
	public PerimetreType update(PerimetreType doc) {
		EntityManager em = getEntityManager();
		try {					
			PerimetreType jpa = get(doc);		
			if (jpa != null && jpa.getPtyId() != null) {
				DataCopier.copyNotIdFields(doc, jpa);
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

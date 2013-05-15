package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.bean.domain.DelegatairePerimetre;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("delegatairePerimetreDao")
public class DelegatairePerimetreDaoImpl extends HibernateGenericDao<DelegatairePerimetre, Integer> implements DelegatairePerimetreDao {

	private static final Logger LOGGER = Logger.getLogger(DelegantPerimetreDaoImpl.class);
			
	public DelegatairePerimetreDaoImpl() {
		super(DelegatairePerimetre.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegatairePerimetre> getByDelegataire(Integer colId) {
		String sql = " from DelegatairePerimetre d where d.delegataire.id = :colId ";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("colId", colId);
		List<DelegatairePerimetre> resultList = query.getResultList();
		return resultList;
	}
	private boolean isExisted(DelegatairePerimetre dp) {
		String sql = " from DelegatairePerimetre d where d.delegataire.id = :colId and d.perimetre.perId = :perId";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("colId", dp.getDelegataire().getId());
		query.setParameter("perId", dp.getPerimetre().getPerId());
		
		List<DelegatairePerimetre> resultList = query.getResultList();
		return (resultList != null && resultList.size() > 0);
	}
	@Transactional
	public DelegatairePerimetre insert(DelegatairePerimetre rec) {
		if (!isExisted(rec)) {
			this.save(rec);
		}
		return rec;				
	}

	@Transactional
	public Boolean deleteByDelegataire(Integer colId) {
		List<DelegatairePerimetre> list = getByDelegataire(colId);
		if (list.isEmpty() == false) {
			for (DelegatairePerimetre item : list) {				
				delete(item);
			}
		}
		return true;
	}	
}
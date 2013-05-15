package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.shared.model.DelegantPerimetreModel;

@Repository("delegantPerimetreDao")
public class DelegantPerimetreDaoImpl extends HibernateGenericDao<DelegantPerimetre, Integer> implements DelegantPerimetreDao {

	private static final Logger LOGGER = Logger.getLogger(DelegantPerimetreDaoImpl.class);
			
	public DelegantPerimetreDaoImpl() {
		super(DelegantPerimetre.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegantPerimetre> getByDelegant(Integer colId) {
		String sql = " from DelegantPerimetre d where d.delegant.id = :colId ";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("colId", colId);
		List<DelegantPerimetre> resultList = query.getResultList();
		return resultList;
	}
		
	private boolean isExisted(DelegantPerimetre dp) {
		String sql = " from DelegantPerimetre d where d.delegant.id = :colId and d.perimetre.perId = :perId";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("colId", dp.getDelegant().getId());
		query.setParameter("perId", dp.getPerimetre().getPerId());
		
		List<DelegantPerimetre> resultList = query.getResultList();
		return (resultList != null && resultList.size() > 0);
	}

	@Transactional
	public DelegantPerimetre insert(DelegantPerimetre rec) {
		if (!isExisted(rec)) {
			this.save(rec);
		}
		return rec;
	}

	@Transactional
	public Boolean deleteByDelegant(Integer colId) {
		List<DelegantPerimetre> list = getByDelegant(colId);
		if (list.isEmpty() == false) {
			for (DelegantPerimetre item : list) {				
				delete(item);
			}
		}
		return true;
	}

	@Transactional
	public void insert(List<DelegantPerimetre> items) {
		for (DelegantPerimetre item: items) {
			this.save(item);
		}
	}	
}
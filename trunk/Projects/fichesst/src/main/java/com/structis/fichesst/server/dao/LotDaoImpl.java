package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Lot;

@Repository
public class LotDaoImpl extends BasicDaoImpl<Lot, Integer> implements LotDao {

	@Override
	public List<Lot> findByNameAndChantier(Integer chantierId, String name) {
		TypedQuery<Lot> query = entityManager.createQuery(
				"from " + Lot.class.getName() + " where chantier.id=:idChantier and upper(nom)=:lotName", Lot.class);
		query.setParameter("idChantier", chantierId);
		query.setParameter("lotName", name.toUpperCase());
		List<Lot> results = query.getResultList();
		
		return results;
	}
}

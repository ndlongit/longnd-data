package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.FicheSt;

@Repository
public class FicheStDaoImpl extends BasicDaoImpl<FicheSt, Integer> implements FicheStDao {

	@Override
	public List<FicheSt> findByChantierId(Integer chantierId) {
		TypedQuery<FicheSt> query = entityManager.createQuery("Select fs from FicheSt fs where fs.lot.chantier.id=:idChantier", FicheSt.class);
		query.setParameter("idChantier", chantierId);
		List<FicheSt> results = query.getResultList();
		return results;
	}
}

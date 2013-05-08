package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.CautionFournie;

@Repository
public class CautionFournieDaoImpl extends BasicDaoImpl<CautionFournie, Integer> implements CautionFournieDao {
	
	@Override
	public List<CautionFournie> findByFicheStId(Integer ficheStId) {
		TypedQuery<CautionFournie> query = entityManager.createQuery("Select cf from CautionFournie cf where cf.ficheSt.id=:idFicheSt", CautionFournie.class);
		query.setParameter("idFicheSt", ficheStId);
		List<CautionFournie> results = query.getResultList();
		return results;
	}
}

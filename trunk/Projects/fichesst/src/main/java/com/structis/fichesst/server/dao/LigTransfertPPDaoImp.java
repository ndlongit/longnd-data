package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.LigTransfertPP;
@Repository("ligTransfertPPDao")
public class LigTransfertPPDaoImp extends BasicDaoImpl<LigTransfertPP, Integer> implements LigTransfertPPDao{

	@Override
	public List<LigTransfertPP> findAllByID(Integer idTransfert,Integer idChantier) {
		TypedQuery<LigTransfertPP> query = entityManager
				.createQuery(
						"Select lig from LigTransfertPP lig left join lig.ficheTransfertPp ft where ft.id.idChantier=:idChantier and ft.id.idTransfertPp=:idTransfert ",
						LigTransfertPP.class);
		query.setParameter("idChantier", idChantier);
		query.setParameter("idTransfert", idTransfert);
		
		List<LigTransfertPP> results = query.getResultList();
		return results;
	}

	@Override
	public void createLig(LigTransfertPP lig) {
		entityManager.persist(lig);
	}

	@Override
	public void deleteLig(LigTransfertPP lig) {
		entityManager.remove(entityManager.merge(lig));
	}

	@Override
	public List<LigTransfertPP> findByChantierId(Integer idChantier) {
		TypedQuery<LigTransfertPP> query = entityManager.createQuery(
				"Select lig from LigTransfertPP lig left join lig.ficheTransfertPp ft where ft.id.idChantier=:idChantier",
				LigTransfertPP.class);
		query.setParameter("idChantier", idChantier);

		List<LigTransfertPP> results = query.getResultList();
		return results;
	}	
}

package com.structis.fichesst.server.dao;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.FicheTransfertppPk;
@Repository("ficheTransfertppDao")
public class FicheTransfertppDaoImpl extends BasicDaoImpl<FicheTransfertpp, FicheTransfertppPk> implements FicheTransfertppDao {


	@Override
	public void createFicheRelation(Integer idChantier, Integer idRefTransfert) {
		FicheTransfertpp ficheTransfertPP=new FicheTransfertpp();
		FicheTransfertppPk pk=new FicheTransfertppPk();
		pk.setIdChantier(idChantier);
		pk.setIdTransfertPp(idRefTransfert);
		ficheTransfertPP.setId(pk);
		entityManager.persist(ficheTransfertPP);
	}
	@Override
	public List<FicheTransfertpp> findbyId(Integer idChantier, Integer idTransfertpp) {
		TypedQuery<FicheTransfertpp> query=entityManager.createQuery("Select ft from FicheTransfertpp ft where ft.id.idChantier=:idChantier and ft.id.idTransfertPp=:idTransfertpp ",FicheTransfertpp.class);
		query.setParameter("idChantier", idChantier);
		query.setParameter("idTransfertpp", idTransfertpp);
		List<FicheTransfertpp> list=query.getResultList();
		return list;
	}
	
}

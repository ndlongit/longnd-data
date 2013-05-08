package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.dao.FicheTransfertppDao;

@Service("ficheTransfertppService")
public class FicheTransfertppServiceImpl extends BasicServiceImpl<FicheTransfertpp, Integer, FicheTransfertppDao> implements
		FicheTransfertppService {

	@Override
	@Transactional
	public void createFicheRelation(Integer idChantier, Integer idRefTransfert) {
		dao.createFicheRelation(idChantier, idRefTransfert);
	}

	@Override
	@Transactional
	public List<FicheTransfertpp> findbyId(Integer idChantier, Integer idTransfertpp) {
		return dao.findbyId(idChantier, idTransfertpp);
	}

	@Override
	public List<FicheTransfertpp> findByChantierId(Integer chantierId) {
		return super.findByProperty("chantier.id", chantierId);
	}
}

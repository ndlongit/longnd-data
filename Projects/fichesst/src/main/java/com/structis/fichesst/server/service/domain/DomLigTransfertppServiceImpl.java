package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.LigTransfertPP;
import com.structis.fichesst.server.dao.LigTransfertPPDao;

@Service("domLigTransfertppService")
public class DomLigTransfertppServiceImpl extends BasicServiceImpl<LigTransfertPP, Integer, LigTransfertPPDao> implements
		DomLigTransfertppService {

	@Override
	public List<LigTransfertPP> findAllByID(Integer idChantier,
			Integer idTransfert) {
		List<LigTransfertPP> result=dao.findAllByID(idChantier, idTransfert);
		return result;
	}

	@Override
	public void createLig(LigTransfertPP lig) {
		dao.createLig(lig);
	}

	@Override
	public void deleteLig(LigTransfertPP lig) {
		dao.deleteLig(lig);
	}
	
	@Override
	public List<LigTransfertPP> findByChantierId(Integer idChantier) {
		List<LigTransfertPP> results = dao.findByChantierId(idChantier);
		return results;
	}
}

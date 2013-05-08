package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.LigTransfertPP;

public interface DomLigTransfertppService extends BasicService<LigTransfertPP, Integer> {
	public List<LigTransfertPP> findAllByID(Integer idChantier,Integer idTransfert);
	void createLig(LigTransfertPP lig);
	void deleteLig(LigTransfertPP lig);
	public List<LigTransfertPP> findByChantierId(Integer idChantier);
	
}

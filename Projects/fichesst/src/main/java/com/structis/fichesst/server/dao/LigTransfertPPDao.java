package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.LigTransfertPP;

public interface LigTransfertPPDao extends BasicDao<LigTransfertPP,Integer> {
	public List<LigTransfertPP> findAllByID(Integer idTransfert,Integer idChantier);
	void createLig(LigTransfertPP lig);
	void deleteLig(LigTransfertPP lig);
	List<LigTransfertPP> findByChantierId(Integer idChantier);
	
}

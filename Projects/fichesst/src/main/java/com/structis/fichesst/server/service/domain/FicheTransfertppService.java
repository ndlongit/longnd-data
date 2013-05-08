package com.structis.fichesst.server.service.domain;
import java.util.List;

import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
public interface FicheTransfertppService extends BasicService<FicheTransfertpp, Integer> {
	 void createFicheRelation(Integer idChantier, Integer idRefTransfert);
	 List<FicheTransfertpp> findbyId(Integer idChantier, Integer idTransfertpp);
	List<FicheTransfertpp> findByChantierId(Integer integer);
}

package com.structis.fichesst.server.dao;
import java.util.List;

import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.FicheTransfertppPk;
public interface FicheTransfertppDao extends BasicDao<FicheTransfertpp, FicheTransfertppPk> {
	List<FicheTransfertpp> findbyId(Integer idChantier,Integer idTransfertpp);
	void createFicheRelation(Integer idChantier,Integer idRefTransfert);
}

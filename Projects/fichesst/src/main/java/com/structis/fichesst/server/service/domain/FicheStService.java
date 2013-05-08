package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;

public interface FicheStService extends BasicService<FicheSt, Integer> {

	List<FicheSt> findByChantierId(Integer chantierId);

	Object updateSynthese(Chantier chantier, List<FicheSt> list1, List<FicheTransfertpp> list2);
}

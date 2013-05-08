package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

public interface ChantierDao extends BasicDao<Chantier,Integer> {
	
	void deleteChantier(Integer idChantier);
	List<Chantier> findChantierByUser(Integer idUser);
	Chantier findChantierbyId(Integer idChantier);
}

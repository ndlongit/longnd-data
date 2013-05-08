package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Gestion;

public interface GestionDao extends BasicDao<Gestion, Integer> {
	public List<Gestion> getGestionByListId(List<Integer> ids);
}

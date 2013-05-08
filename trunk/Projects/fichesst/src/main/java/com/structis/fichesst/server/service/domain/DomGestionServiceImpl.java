package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.server.dao.GestionDao;

@org.springframework.stereotype.Service("domGestionService")
public class DomGestionServiceImpl extends BasicServiceImpl<Gestion, Integer, GestionDao> implements DomGestionService {
	
	@Autowired
	GestionDao gestionDao;
	@Override
	@Transactional(readOnly = true)
	public List<Gestion> getGestionByListId(List<Integer> ids) {
		List<Gestion> listGestion = gestionDao.getGestionByListId(ids);
		return listGestion;

	}
}

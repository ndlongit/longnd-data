package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.CollaborateurFormation;
import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.dao.support.GenericDao;

public interface CollaborateurFormationDao extends GenericDao<CollaborateurFormation, Integer> {
	
	List<CollaborateurFormation> findByCollaborateurId(Integer id);
	CollaborateurFormation insert(CollaborateurFormation model);
	CollaborateurFormation update(CollaborateurFormation model);
	Boolean deleteByCollaborateurId(Integer id);
	List<Formation> insertList(Collaborateur collaborateur, List<Formation> list);
	CollaborateurFormation findByColIdAndForId(Integer colId, Integer forId);
	List<CollaborateurFormation> findByFormationId(Integer forId);
}

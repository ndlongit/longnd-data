package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.dao.support.GenericDao;

public interface CollaborateurDao extends GenericDao<Collaborateur, Integer> {

	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie);
	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId,
			Boolean sortie, String sortedField,Integer sortDir, Integer start, Integer pageSize);

	public List<Collaborateur> getAllDelegantsByEntiteId(String entiteId);

	public List<Collaborateur> getAllDelegatairesByEntiteId(String entiteId);

	public Collaborateur findByIdBycn(String idBycn);
	
	public Collaborateur update(Collaborateur collaborateur);
	
	public Collaborateur insert(Collaborateur collaborateur);

	public List<Collaborateur> getAllCollaborateurs();

	public List<Collaborateur> findByName(String name, String entityId, Boolean sortie);
	public List<Collaborateur> findByName(String name, String entityId,
			Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize);

	public List<Collaborateur> getAllDelegantsByPerimeter(String perId, String entiteId);

	public List<Collaborateur> getAllDelegatairesByPerimeter(String perId, String entiteId);
	
	public List<Collaborateur> getAllDelegantsByParentPerimeter(String perId, String entiteId);

	public List<Collaborateur> getAllDelegatairesByParentPerimeter(String perId, String entiteId);
	
	public List<Collaborateur> findByPerimetre(String perimetreId);

	public List<Collaborateur> getDelegantsByNatureAndPerimetre(String perId, String ptyId,
			String entId, Integer natureId);

	public List<KeyValueVM> getAllKeyValueCollaborateursByEntiteId(
			String entiteId, boolean sorti);
	public Long countByName(String name, String entId, Boolean sortie);
	public Long countAllCollaborateursByEntiteId(String entId, Boolean sortie);
	public String getAllParentPerimetreAndCurrent(String entId, String perId);	
}

package com.structis.vip.server.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.CollaborateurFormation;
import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.bean.domain.DelegatairePerimetre;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.dao.CollaborateurDao;
import com.structis.vip.server.dao.CollaborateurFormationDao;
import com.structis.vip.server.dao.DelegantPerimetreDao;
import com.structis.vip.server.dao.DelegatairePerimetreDao;
import com.structis.vip.server.dao.PerimetreDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.DelegantPerimetreModel;

@Service("domCollaborateurService")
public class DomCollaborateurServiceImpl extends GenericEntityServiceImpl<Collaborateur, Integer> implements
		DomCollaborateurService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomCollaborateurServiceImpl.class);

	@Autowired
	@Qualifier("collaborateurDao")
	private CollaborateurDao collaborateurDao;
	
	@Autowired
	@Qualifier("collaborateurFormationDao")
	private CollaborateurFormationDao collaborateurFormationDao;
	
	@Autowired
	@Qualifier("perimetreDao")
	private PerimetreDao perimetreDao;
	
	@Autowired
	@Qualifier("delegantPerimetreDao")
	private DelegantPerimetreDao delegantPerimetreDao;
	
	@Autowired
	@Qualifier("delegatairePerimetreDao")
	private DelegatairePerimetreDao delegatairePerimetreDao;

	@Override
	public GenericDao<Collaborateur, Integer> getDao() {
		return collaborateurDao;
	}

	@Override
	public Collaborateur getNew() {
		return new Collaborateur();
	}

	@Override
	public Collaborateur getNewWithDefaults() {
		return this.getNew();
	}

	/**
	 * Get all entites
	 */
	public List<Collaborateur> getAllCollaborateurs() {
		return collaborateurDao.getAllCollaborateurs();
	}

	@Override
	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie) {
		return collaborateurDao.getAllCollaborateursByEntiteId(entiteId, sortie);
	}
	@Override
	public List<Collaborateur> getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize) {
		return collaborateurDao.getAllCollaborateursByEntiteId(entiteId, sortie, sortedField, sortDir, start, pageSize);
	}

	@Override
	public List<Collaborateur> getAllDelegantsByEntiteId(String entiteId) {
		return collaborateurDao.getAllDelegantsByEntiteId(entiteId);
	}

	@Override
	public List<Collaborateur> getAllDelegatairesByEntiteId(String entiteId) {
		return collaborateurDao.getAllDelegatairesByEntiteId(entiteId);
	}

	@Override
	public Collaborateur findByIdBycn(String idBycn) {
		return collaborateurDao.findByIdBycn(idBycn);
	}

	@Override
	public Collaborateur update(Collaborateur collaborateur) {
		Collaborateur col = collaborateurDao.update(collaborateur);
		
		return col;
	}

	@Override
	public Collaborateur insert(Collaborateur collaborateur) {
		Collaborateur col = collaborateurDao.insert(collaborateur);
		if (col != null) {
			col.setFormations(collaborateurFormationDao.insertList(collaborateur, col.getFormations()));
		}
		return col;
	}

	@Override
	public List<Collaborateur> findByName(String name, String entityId, Boolean sortie) {
		if (name == null || "".equals(name)) {
			return getAllCollaborateursByEntiteId(entityId, sortie);
		}
		return collaborateurDao.findByName(name, entityId, sortie);
	}
	@Override
	public List<Collaborateur> findByName(String name, String entityId,
			Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize) {
		if (name == null || "".equals(name)) {
			return getAllCollaborateursByEntiteId(entityId, sortie, sortedField, sortDir, start, pageSize);
		}
		return collaborateurDao.findByName(name, entityId, sortie, sortedField, sortDir, start, pageSize);
	}

	@Override
	public List<CollaborateurFormation> findByCollaborateurId(Integer colId) {
		return collaborateurFormationDao.findByCollaborateurId(colId);
	}
	
	@Override
	public List<CollaborateurFormation> findByFormationId(Integer forId) {
		return collaborateurFormationDao.findByFormationId(forId);
	}

	@Override
	public Collaborateur updateAndFormation(Collaborateur collaborateur) {
		Collaborateur col = collaborateurDao.update(collaborateur);
		if (col != null) {
			col.setFormations(collaborateurFormationDao.insertList(collaborateur, col.getFormations()));
		}
		return col;
	}

	@Override
	public Collaborateur updatePerimetreDelegant(Integer colId, String perId) {
		Collaborateur col = this.getByPrimaryKey(colId);
		if (col != null) {
			if (perId != null) {
				Perimetre perimetre = new Perimetre();
				perimetre.setPerId(perId);
				perimetre = perimetreDao.get(perimetre);
				col.setPerimetreDelegant(perimetre);
			} else {
				col.setPerimetreDelegant(null);
			}
			collaborateurDao.update(col);
		}
		return col;
	}
	
	@Override
	public Collaborateur updatePerimetreDelegataire(Integer colId, String perId) {
		Collaborateur col = this.getByPrimaryKey(colId);
		if (col != null) {
			if (perId != null) {
				Perimetre perimetre = new Perimetre();
				perimetre.setPerId(perId);
				perimetre = perimetreDao.get(perimetre);
				col.setPerimetreDelegataire(perimetre);
			} else {
				col.setPerimetreDelegataire(null);
			}
			collaborateurDao.update(col);
		}
		return col;
	}

	@Override
	public List<Collaborateur> getAllDelegantsByPerimeter(String perId, String entiteId) {
		List<Collaborateur> result = new ArrayList<Collaborateur>();
		Perimetre p = new Perimetre();
		p.setPerId(perId);
		p = perimetreDao.get(p);
		if (p != null) {
			result = new ArrayList<Collaborateur>();;
			result = collaborateurDao.getAllDelegantsByPerimeter(perId, entiteId);
			while (p.getParent() != null) {
				p = p.getParent();
				copyUnique(result, collaborateurDao.getAllDelegantsByParentPerimeter(p.getPerId(), entiteId) );				
			}
		}
		return result;
	}

	@Override
	public List<Collaborateur> getAllDelegatairesByPerimeter(String perId, String entiteId) {
		List<Collaborateur> result = new ArrayList<Collaborateur>();		
		Perimetre p = new Perimetre();
		p.setPerId(perId);
		p = perimetreDao.get(p);
		if (p != null) {
			result = new ArrayList<Collaborateur>();;
			result = collaborateurDao.getAllDelegatairesByPerimeter(perId, entiteId);
			while (p.getParent() != null) {
				p = p.getParent();
				copyUnique(result, collaborateurDao.getAllDelegatairesByParentPerimeter(p.getPerId(), entiteId));				
			}			
		}
		return result;
	}
	private void copyUnique(List<Collaborateur> toList, List<Collaborateur> subList) {
		for (Collaborateur d: subList) {
			if (!toList.contains(d)) {
				toList.add(d);
			}
		}
		
	}

	@Override
	public List<Collaborateur> findByPerimetre(String perimetreId) {
		return collaborateurDao.findByPerimetre(perimetreId); 
	}

	@Override
	public Boolean deleteFormation(Integer id) {
		return collaborateurFormationDao.deleteByCollaborateurId(id);
	}

	@Override
	public List<Collaborateur> getDelegantsByNatureAndPerimetre(String perId, String ptyId,
			String entId, Integer natureId) {
		List<Collaborateur> result = new ArrayList<Collaborateur>();		
		
		result = collaborateurDao.getDelegantsByNatureAndPerimetre(perId, ptyId, entId, natureId);
		
		return result;
	}

	@Override
	public List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId) {		
		return collaborateurDao.getAllKeyValueCollaborateursByEntiteId(entiteId, false);		
	}

	@Override
	public void deleteDelegantPerimetres(Integer delegantId) {
		delegantPerimetreDao.deleteByDelegant(delegantId);		
	}

	@Override
	public void insertDelegantPerimetre(DelegantPerimetre item) {
		delegantPerimetreDao.insert(item);
	}

	@Override
	public List<DelegantPerimetre> findPerimetresByDelegant(Integer colId) {
		return delegantPerimetreDao.getByDelegant(colId);
	}

	@Override
	public void deleteDelegatairePerimetres(Integer id) {
		delegatairePerimetreDao.deleteByDelegataire(id);
		
	}

	@Override
	public void insertDelegatairePerimetre(DelegatairePerimetre item) {
		delegatairePerimetreDao.insert(item);
		
	}

	@Override
	public List<DelegatairePerimetre> findPerimetresByDelegataire(Integer colId) {
		return delegatairePerimetreDao.getByDelegataire(colId);
	}

	@Override
	public Collaborateur findById(Integer colId) {
		return this.getByPrimaryKey(colId);
	}

	@Override
	public Long countByName(String name, String entId, Boolean sortie) {
		if (name == null || "".equals(name)) {
			return countAllCollaborateursByEntiteId(entId, sortie);
		}
		return collaborateurDao.countByName(name, entId, sortie);
	}

	public Long countAllCollaborateursByEntiteId(String entId, Boolean sortie) {
		return collaborateurDao.countAllCollaborateursByEntiteId(entId, sortie);
	}

	
}

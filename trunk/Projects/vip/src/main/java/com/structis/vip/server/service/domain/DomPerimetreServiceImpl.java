package com.structis.vip.server.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.dao.PerimetreDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.PerimetreModel;

@Service("domPerimetreService")
public class DomPerimetreServiceImpl extends GenericEntityServiceImpl<Perimetre, String>
		implements DomPerimetreService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(DomPerimetreServiceImpl.class);

	@Autowired
	@Qualifier("perimetreDao")
	private PerimetreDao perimetreDao;
	

	@Override
	public GenericDao<Perimetre, String> getDao() {
		return perimetreDao;
	}

	@Override
	public Perimetre getNew() {
		return new Perimetre();
	}

	@Override
	public Perimetre getNewWithDefaults() {
		return this.getNew();
	}
	/**
	 * Get all perimetre for an entity
	 */
	public  List<Perimetre> findPerimetreByEntite(String entiteId){		
		return perimetreDao.findPerimetreByEntite(entiteId);
	}

	/**
	 * Get list of 1st level perimetre for 1 entity
	 */
	@Override
	public List<Perimetre> findFirstLevelPerimetreByEntite(String entiteId) {
		return perimetreDao.findFirstLevelPerimetreByEntite(entiteId);
	}

	@Override
	public List<PerimetreModel> getTreeModel(String entiteId, String perimetreId) {
		return perimetreDao.getTreeModel(entiteId, perimetreId);
	}

	@Override
	public Boolean insert(Perimetre perimetre) {
		try {
			this.save(perimetre);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Perimetre perimetre) {
		if (perimetre.getEntite().getEntId().equals(Constants.ENTITE_ID_ETDE)) {
			return perimetreDao.update(perimetre);
		} else {
			boolean isBreak = false;
			if (perimetre.getChantierType() != null && perimetre.getChantierType().getLabel().equalsIgnoreCase("Parfait achevement")) {
				Perimetre old = this.findById(perimetre.getPerId());
				// remove this rule - Philippe Michel command is different to Philippe Nguyen :(.  
//				if (old.getChantierType() != null && old.getChantierType().getLabel().equalsIgnoreCase("Classique")) {
//					if (perimetreDao.isBreakRuleClassiqueToParfait(perimetre)) {
//						isBreak = true;
//					} 
//				}
			} 
			if (!isBreak) {
				return perimetreDao.update(perimetre);
			} else {
				return false;
			}
		}
	}

	@Override
	public Perimetre findById(String id) {
		return this.getByPrimaryKey(id);
	}

	@Override
	public List<Perimetre> findFirstLevelPerimetre() {
		return perimetreDao.findFirstLevelPerimetre();
	}
	
	@Override
	public Integer getNewIndex() {
		return perimetreDao.getNewIndex();
	}

	@Override
	public List<Perimetre> getTreeModelByParent(String entiteId, String perimetreId) {
		return perimetreDao.getTreeModelByParent(entiteId, perimetreId);
	}

	@Override
	public List<Perimetre> findParents(String perimetreId) {
		List<Perimetre> parents = new ArrayList<Perimetre>();
		Perimetre perimetre = findById(perimetreId);
		while (perimetre.getParent() != null) {
			parents.add(perimetre.getParent());
			perimetre = findById(perimetre.getParent().getPerId());
		}
		return parents;
	}

	@Override
	public List<Perimetre> findPerimetreByEntiteJuri(Integer entiteJuriId) {
		return perimetreDao.findPerimetreByEntiteJuri(entiteJuriId);
	}

	@Override
	public List<Perimetre> findPerimetreByLanguage(Integer languageId) {
		return perimetreDao.findPerimetreByLanguage(languageId);
	}

	@Override
	public List<Perimetre> findByChantierTypeId(Integer chantierTypeId) {
		return perimetreDao.findByChantierTypeId(chantierTypeId);
	}

	@Override
	public boolean isPerimetreChild(String compareId, String parentPer) {
		return perimetreDao.isPerimetreChildOf(compareId, parentPer);
	}

	@Override
	public List<Perimetre> findByPerimetreTypeId(String ptyId) {
		return perimetreDao.findByPerimetreTypeId(ptyId);
	}

	@Override
	public Integer hasReferenceInDelegationOrControlOrPerimetre(String perId) {
		return perimetreDao.hasReferenceInDelegationOrControlOrPerimetre(perId);
	}

	@Override
	public Integer hasReferenceInUserCollaborateur(String perId) {		
		return perimetreDao.hasReferenceInUserCollaborateur(perId);
	}

	@Override
	public void clearReferenceToPerimetreInUserCollaborateur(String perId) {
		perimetreDao.clearReferenceToPerimetreInUserCollaborateur(perId);
	}

	@Override
	public List<Perimetre> findAndDeleteOrphanPerimetresNotInArgosByEntite(String entiteId,String parentId, 
			List<String> idsInArgos) {
		return perimetreDao.findAndDeleteOrphanPerimetresNotInArgosByEntite(entiteId, parentId, idsInArgos);
	}

	@Override
	public List<Perimetre> findByPerimetreParent(String perId) {
		return perimetreDao.findByPerimetreParent(perId);
	}
}

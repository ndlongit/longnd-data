package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.shared.model.PerimetreModel;

public interface PerimetreDao extends GenericDao<Perimetre, String> {
	
	public List<Perimetre> findPerimetreByEntite(String entiteId);

	public List<Perimetre> findFirstLevelPerimetreByEntite(String entiteId);
	
	public List<PerimetreModel> getTreeModel(String entiteId, String perimetreId);

	public List<PerimetreModel> getTreeModel(PerimetreModel perimetreModel);

	public Boolean update(Perimetre perimetre);

	public List<Perimetre> findFirstLevelPerimetre();
	
	public Integer getNewIndex();
	
	public List<Perimetre> getTreeModelByParent(String entiteId, String perimetreId);

	public List<Perimetre> findPerimetreByEntiteJuri(Integer entiteJuriId);

	public List<Perimetre> findPerimetreByLanguage(Integer languageId);

	public List<Perimetre> findByChantierTypeId(Integer chantierTypeId);

	public boolean isPerimetreChildOf(String compareId, String parentPer);

	public List<Perimetre> findByPerimetreTypeId(String ptyId);

	public boolean isBreakRuleClassiqueToParfait(Perimetre perimetre);
	
	public Integer hasReferenceInDelegationOrControlOrPerimetre(String perId);
	public Integer hasReferenceInUserCollaborateur(String perId);
	public void clearReferenceToPerimetreInUserCollaborateur(String perId);

	public List<Perimetre> findAndDeleteOrphanPerimetresNotInArgosByEntite(String entiteId,String parentId, 
			List<String> idsInArgos);

	public List<Perimetre> findByPerimetreParent(String perId);
	
}

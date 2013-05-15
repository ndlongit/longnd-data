package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.model.PerimetreModel;

public interface DomPerimetreService extends GenericEntityService<Perimetre, String> {
	List<Perimetre> findPerimetreByEntite(String entiteId);

	List<Perimetre> findFirstLevelPerimetreByEntite(String entiteId);

	List<Perimetre> findFirstLevelPerimetre();

	List<PerimetreModel> getTreeModel(String entiteId, String perimetreId);

	List<Perimetre> getTreeModelByParent(String entiteId, String perimetreId);
	
	List<Perimetre> findParents(String perimetreId);

	Boolean insert(Perimetre perimetre);

	Boolean update(Perimetre perimetre);

	Perimetre findById(String id);

	Integer getNewIndex();

	List<Perimetre> findPerimetreByEntiteJuri(Integer entiteJuriId);

	List<Perimetre> findPerimetreByLanguage(Integer languageId);

	List<Perimetre> findByChantierTypeId(Integer chantierTypeId);

	boolean isPerimetreChild(String compareId, String parentPer);

	List<Perimetre> findByPerimetreTypeId(String ptyId);

	Integer hasReferenceInDelegationOrControlOrPerimetre(String perId);

	Integer hasReferenceInUserCollaborateur(String perId);

	void clearReferenceToPerimetreInUserCollaborateur(String perId);

	List<Perimetre> findAndDeleteOrphanPerimetresNotInArgosByEntite(String entiteId,String parentId, 
			List<String> idsInArgos);

	List<Perimetre> findByPerimetreParent(String perId);
}

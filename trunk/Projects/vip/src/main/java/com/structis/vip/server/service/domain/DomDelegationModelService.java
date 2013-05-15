package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDelegationModelService extends GenericEntityService<DelegationMdl, Integer> {

	List<DocumentMdl> getDocumentModels(Integer langId, String ptyId, Integer natureId, Integer collaboraterType);

	Object getDocumentModels(Integer langId, String ptyId, Integer id2, String entId, Integer collaboraterType);

	List<DelegationMdl> getDelegationModels(String entId, Integer natureId);

	List<DelegationMdl> getDelegationModelsByGroup(Integer group);

	Integer getGroup(Integer langId, String ptyId, Integer natureId, Integer collaboraterType);

	Integer getGroup(Integer langId, String ptyId, Integer natureId, String entId, Integer collaboraterType);
	
	List<DelegationMdl> getAllDelegationModelsByEntite(String entiteId);

	DelegationMdl update(DelegationMdl dm);

	void updateBatch(List<DelegationMdl> dms);

	void insertBatch(List<DelegationMdl> dms);

	DelegationMdl insert(DelegationMdl dm);

	Boolean existOtherDelegationModel(DelegationMdl dm);

	DelegationMdl findById(Integer id);

	DelegationMdl insert(DelegationMdl dm, Integer group);

	Integer getLastGroup();
	
	Boolean deleteByGroup(Integer group);

	List<DelegationMdl> findByLanguageId(Integer languageId);

	List<DelegationMdl> findByNatureId(Integer natureId);

	Boolean getHasMutiDelegataire(Integer group);
}

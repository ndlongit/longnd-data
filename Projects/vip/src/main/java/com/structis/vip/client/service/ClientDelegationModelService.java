package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationModelService")
public interface ClientDelegationModelService extends RemoteService {
	List<DocumentMdlModel> getDocumentModels(LanguageModel lang, PerimetreTypeModel perimetre,
			DelegationNatureModel nature, Integer collaboraterType);

	List<DocumentMdlModel> getDocumentModels(LanguageModel lang, PerimetreTypeModel perimetre,
			DelegationNatureModel nature, EntiteModel entite, Integer collaboraterType);

	Integer getGroup(LanguageModel lang, PerimetreTypeModel perimetre,
			DelegationNatureModel nature, Integer collaboraterType);

	Integer getGroup(LanguageModel lang, PerimetreTypeModel perimetre,
			DelegationNatureModel nature, EntiteModel entite, Integer collaboraterType);
	
	List<DelegationMdlModel> getDelegationModelBy(EntiteModel entite, DelegationNatureModel nature);

	List<DelegationMdlModel> getDelegationModelsByGroup(Integer group);
	
	List<DelegationMdlModel> getAllDelegationModelsByEntite(EntiteModel entite);

	DelegationMdlModel update(DelegationMdlModel dem) throws DelegationException;

	DelegationMdlModel insert(DelegationMdlModel dem) throws DelegationException;

	void updateBatch(List<DelegationMdlModel> dems);

	void insertBatch(List<DelegationMdlModel> dems);

	Boolean delete(DelegationMdlModel dem) throws DelegationException;

	DelegationMdlModel findById(Integer id);

	DelegationMdlModel insert(DelegationMdlModel dem, Integer group);
	
	Integer insert(List<DelegationMdlModel> dems, Integer group);
	
	Boolean deleteByGroup(Integer group);
	Boolean getHasMutiDelegataire(Integer group);
}

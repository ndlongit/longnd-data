package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDelegationModelService;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.dao.DelegationModelDao;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationModelService;
import com.structis.vip.server.service.domain.DomFieldRuleService;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

@Service("clientDelegationModelServiceImpl")
public class ClientDelegationModelServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientDelegationModelService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
		.getLogger(ClientDelegationModelServiceImpl.class);
	
	@Autowired
	private DomDelegationModelService domDelegationModelService;
	
	@Autowired
	private DomFieldRuleService domFieldRuleService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@Override
	public List<DocumentMdlModel> getDocumentModels(final LanguageModel lang,
			final PerimetreTypeModel perimetreType, final DelegationNatureModel nature,
			final Integer collaboraterType) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationModelService.getDocumentModels(lang.getId(), perimetreType.getPtyId(), nature.getId(), collaboraterType);
			}
		};
		return (List<DocumentMdlModel>) callManager(callBack);
	}

	@Override
	public List<DocumentMdlModel> getDocumentModels(final LanguageModel lang,
			final PerimetreTypeModel perimetre, final DelegationNatureModel nature,
			final EntiteModel entite, final Integer collaboraterType) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationModelService.getDocumentModels(lang.getId(), perimetre.getPtyId(), nature.getId(), entite.getEntId(), collaboraterType);
			}
		};
		return (List<DocumentMdlModel>) callManager(callBack);
	}

	@Override
	public List<DelegationMdlModel> getDelegationModelBy(final EntiteModel entite,
			final DelegationNatureModel nature) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationModelService.getDelegationModels(entite.getEntId(), nature.getId());
			}
		};
		return (List<DelegationMdlModel>) callManager(callBack);
	}

	@Override
	public DelegationMdlModel update(DelegationMdlModel dem) throws DelegationException {				
		DelegationMdl dm = (DelegationMdl)modelBeanMapper.map(dem);
		if (domDelegationModelService.existOtherDelegationModel(dm)) {
			throw new DelegationException(ExceptionType.DELEGATION_MODEL_EXIST);
		} else {
			dm = domDelegationModelService.update(dm);		
			return (DelegationMdlModel)modelBeanMapper.map(dm);
		}
	}

	@Override
	public DelegationMdlModel insert(DelegationMdlModel dem) throws DelegationException {
		DelegationMdl dm = (DelegationMdl)modelBeanMapper.map(dem);
		if (domDelegationModelService.existOtherDelegationModel(dm)) {
			throw new DelegationException(ExceptionType.DELEGATION_MODEL_EXIST);
		} else {
			dm = domDelegationModelService.insert(dm);
			return (DelegationMdlModel)modelBeanMapper.map(dm);
		}
	}

	@Override
	public void updateBatch(List<DelegationMdlModel> dems) {
//		List<DelegationMdl> dms = (List<DelegationMdl>)modelBeanMapper.map(dems);
//		domDelegationModelService.updateBatch(dms);		
	}

	@Override
	public void insertBatch(List<DelegationMdlModel> dems) {
//		List<DelegationMdl> dms = (List<DelegationMdl>)modelBeanMapper.map(dems);
//		domDelegationModelService.insertBatch(dms);
	}

	@Override
	public Boolean delete(DelegationMdlModel dem) throws DelegationException {
		List<FieldRule> rules = domFieldRuleService.getRulesByDemGroup(dem.getGroup());
		if (rules != null && rules.size() > 0) {
			throw new DelegationException(ExceptionType.DELEGATION_MODEL_USED_IN_RULES);
		} else {
			DelegationMdl delegationMdl = domDelegationModelService.findById(dem.getId());
			domDelegationModelService.delete(delegationMdl);
			return true;
		}		
	}

	@Override
	public DelegationMdlModel findById(Integer id) {		
		DelegationMdl dm = domDelegationModelService.findById(id);
		return (DelegationMdlModel)modelBeanMapper.map(dm);
	}

	@Override
	public DelegationMdlModel insert(DelegationMdlModel dem, Integer group) {
		DelegationMdl dm = (DelegationMdl)modelBeanMapper.map(dem);
		if (domDelegationModelService.existOtherDelegationModel(dm)) {
			return null;
		} else {
			dm = domDelegationModelService.insert(dm, group);
			return (DelegationMdlModel)modelBeanMapper.map(dm);
		}	
	}

	@Override
	public Integer insert(List<DelegationMdlModel> dems, Integer group) {
		if (group == 0) {
			group = domDelegationModelService.getLastGroup();
		}
		int i = 0;
		for (DelegationMdlModel dem : dems) {
			if (this.insert(dem, group) != null) {
				i++;
			}
		}
		if (i != 0) {
			return group;
		} else {
			return 0;
		}
	}

	@Override
	public Boolean deleteByGroup(Integer group) {
		return domDelegationModelService.deleteByGroup(group);
	}

	@Override
	public List<DelegationMdlModel> getDelegationModelsByGroup(final Integer group) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationModelService.getDelegationModelsByGroup(group);
			}
		};
		return (List<DelegationMdlModel>) callManager(callBack);
	}

	@Override
	public List<DelegationMdlModel> getAllDelegationModelsByEntite(final EntiteModel entite) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationModelService.getAllDelegationModelsByEntite(entite.getEntId());
			}
		};
		return (List<DelegationMdlModel>) callManager(callBack);		
	}

	@Override
	public Integer getGroup(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature,
			Integer collaboraterType) {
		return domDelegationModelService.getGroup(lang.getId(), perimetre.getPtyId(), nature.getId(), collaboraterType);
	}

	@Override
	public Integer getGroup(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature,
			EntiteModel entite, Integer collaboraterType) {
		return domDelegationModelService.getGroup(lang.getId(), perimetre.getPtyId(), nature.getId(), entite.getEntId(), collaboraterType);
	}

	@Override
	public Boolean getHasMutiDelegataire(Integer group) {
		return domDelegationModelService.getHasMutiDelegataire(group);
	}
}

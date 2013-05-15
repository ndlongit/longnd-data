package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientLanguageService;
import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.Entite;
import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.bean.domain.Language;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationModelService;
import com.structis.vip.server.service.domain.DomDocumentModelService;
import com.structis.vip.server.service.domain.DomEntiteService;
import com.structis.vip.server.service.domain.DomFieldService;
import com.structis.vip.server.service.domain.DomLanguageService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.LanguageModel;

@Service("clientLanguageService")
public class ClientLanguageServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientLanguageService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientLanguageServiceImpl.class);
	
	@Autowired
	private DomLanguageService domLanguageService; 
	
	@Autowired
	private DomEntiteService domEntiteService;
	
	@Autowired
	private DomPerimetreService domPerimetreService;
	
	@Autowired
	private DomDocumentModelService domDocumentModelService;
	
	@Autowired
	private DomDelegationModelService domDelegationModelService;
	
	@Autowired
	private DomFieldService domFieldService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<LanguageModel> getLanguages() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domLanguageService.find();
			}
		};
		return (List<LanguageModel>) callManager(callBack);
	}

	@Override
	public LanguageModel findById(Integer languageId) {
		Language lg = domLanguageService.getByPrimaryKey(languageId);
		return (LanguageModel) modelBeanMapper.map(lg);
	}

	@Override
	public Boolean delete(LanguageModel model) {
		List<Entite> entites = domEntiteService.findByLanguageId(model.getId());
		if (entites.isEmpty() == false) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_EXIST_IN_ENTITE);
		}
		
		List<Perimetre> perimetres = domPerimetreService.findPerimetreByLanguage(model.getId());
		if (perimetres.isEmpty() == false) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_EXIST_IN_PERIMETRE);
		}
		
		List<DelegationMdl> delegationModels = domDelegationModelService.findByLanguageId(model.getId());
		if (delegationModels.isEmpty() == false) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_EXIST_IN_DELEGATION_MODEL);
		}
		
		List<DocumentMdl> documentModels = domDocumentModelService.getDocumentModelsByLanguage(model.getId());
		if (documentModels.isEmpty() == false) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_EXIST_IN_DOCUMENT_MODEL);
		}
		
		List<FieField> fields = domFieldService.findByLanguageId(model.getId());
		if (fields.isEmpty() == false) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_EXIST_IN_FIELD);
		}
		
		Language defLanguage = domLanguageService.getDefaultLanguage();
		if (defLanguage != null && defLanguage.getId().intValue() == model.getId().intValue()) {
			throw new LanguageException(ExceptionType.LANGUAGE_DELETE_DEFAULT);
		}
		
		Language dm = (Language) modelBeanMapper.map(model);
		domLanguageService.delete(dm);
		return true;
	}

	@Override
	public LanguageModel insert(LanguageModel model) {
		Language doc = (Language) modelBeanMapper.map(model);
		doc = domLanguageService.insert(doc);
		return (LanguageModel) modelBeanMapper.map(doc);
	}

	@Override
	public LanguageModel update(LanguageModel model) {
		Language doc = (Language) modelBeanMapper.map(model);
		doc = domLanguageService.update(doc);
		return (LanguageModel) modelBeanMapper.map(doc);
	}
	
}

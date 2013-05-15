package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientEntiteJuridiqueService;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomEntiteJuridiqueService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.shared.exception.EntiteJuridiqueException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

@Service("clientEntiteJuridiqueService")
public class ClientEntiteJuridiqueServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientEntiteJuridiqueService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientEntiteJuridiqueServiceImpl.class);
	
	@Autowired
	private DomEntiteJuridiqueService domEntiteJuriService;
	
	@Autowired
	private DomPerimetreService domPerimetreService;
	
	@Autowired
	private DomDelegationService domDelegationService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@Override
	public EntiteJuridiqueModel findById(Integer id) {
		EntiteJuridique entite = domEntiteJuriService.findById(id);
		return (EntiteJuridiqueModel) modelBeanMapper.map(entite);
	}

	@Override
	public EntiteJuridiqueModel insert(EntiteJuridiqueModel model) {
		EntiteJuridique doc = (EntiteJuridique) modelBeanMapper.map(model);
		doc = domEntiteJuriService.insert(doc);
		return (EntiteJuridiqueModel) modelBeanMapper.map(doc);
	}

	@Override
	public EntiteJuridiqueModel update(EntiteJuridiqueModel model) {
		EntiteJuridique doc = (EntiteJuridique) modelBeanMapper.map(model);
		doc = domEntiteJuriService.update(doc);
		return (EntiteJuridiqueModel) modelBeanMapper.map(doc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntiteJuridiqueModel> findByEntiteId(final String entId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domEntiteJuriService.findByEntiteId(entId);
			}
		};
		return (List<EntiteJuridiqueModel>) callManager(callBack);
	}

	@Override
	public boolean delete(EntiteJuridiqueModel model) {
		List<Perimetre> perimetres = domPerimetreService.findPerimetreByEntiteJuri(model.getId());
		if (perimetres.isEmpty() == false) {
			throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_EXIST_IN_PERIMETRE);
		} 

		EntiteJuridique defEntiteJuri = domEntiteJuriService.getDefaultByEntiteId(model.getEntite().getEntId());
		if (defEntiteJuri != null && defEntiteJuri.getId().intValue() == model.getId().intValue()) {
			throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_DEFAULT);
		}
		
		List<Delegation> delegations = domDelegationService.findByEntiteJuriId(model.getId());
		if (delegations.isEmpty() == false) {
			throw new EntiteJuridiqueException(ExceptionType.ENTITE_JUR_DELETE_EXIST_IN_DELEGATION);
		} 
		
		EntiteJuridique dm = (EntiteJuridique) modelBeanMapper.map(model);
		domEntiteJuriService.delete(dm);
		return true;
	}

	@Override
	public EntiteJuridiqueModel getDefaultByEntiteId(String entId) {
		EntiteJuridique entite = domEntiteJuriService.getDefaultByEntiteId(entId);
		return (EntiteJuridiqueModel) modelBeanMapper.map(entite);
	}
}

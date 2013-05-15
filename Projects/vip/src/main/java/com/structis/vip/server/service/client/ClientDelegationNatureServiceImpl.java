package com.structis.vip.server.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDelegationNatureService;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DelegationMdl;
import com.structis.vip.server.bean.domain.DelegationNature;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationModelService;
import com.structis.vip.server.service.domain.DomDelegationNatureService;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.NatureException;
import com.structis.vip.shared.model.DelegationNatureModel;

@Service("clientDelegationNatureService")
public class ClientDelegationNatureServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientDelegationNatureService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientDelegationNatureServiceImpl.class);

	@Autowired
	private DomDelegationNatureService domDelegationNatureService;
	
	@Autowired
	private DomDelegationService domDelegationService;
	
	@Autowired
	private DomDelegationModelService domDelegationModelService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationNatureModel> findNatureByEntite(final String entiteId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationNatureService.findNatureByEntite(entiteId);
			}
		};
		return (List<DelegationNatureModel>) callManager(callBack);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(final String entiteId, final String ptyId, final Boolean isSub) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationNatureService.findNatureByEntiteAndPerimetreType(entiteId, ptyId, isSub);
			}
		};
		return (List<DelegationNatureModel>) callManager(callBack);
	}

	@Override
	public Boolean delete(DelegationNatureModel model) {
		List<Delegation> delegations = domDelegationService.findByNatureId(model.getId());
		if (delegations.isEmpty() == false) {
			throw new NatureException(ExceptionType.NATURE_DELETE_EIXIST_IN_DELEGATION);
		}
		
		List<DelegationMdl> delegationModels = domDelegationModelService.findByNatureId(model.getId());
		if (delegationModels.isEmpty() == false) {
			throw new NatureException(ExceptionType.NATURE_DELETE_EIXIST_IN_DELEGATION_MODEL);
		}
		
		DelegationNature dm = (DelegationNature) modelBeanMapper.map(model);
		domDelegationNatureService.delete(dm);
		return true;
	}

	@Override
	public DelegationNatureModel insert(DelegationNatureModel model) {
		DelegationNature doc = (DelegationNature) modelBeanMapper.map(model);
		doc = domDelegationNatureService.insert(doc);
		return (DelegationNatureModel) modelBeanMapper.map(doc);
	}

	@Override
	public DelegationNatureModel update(DelegationNatureModel model) {
		DelegationNature doc = (DelegationNature) modelBeanMapper.map(model);
		doc = domDelegationNatureService.update(doc);
		return (DelegationNatureModel) modelBeanMapper.map(doc);
	}

	/**
	 * Should filter natures for that perimetre type
	 */
	@Override
	public List<DelegationNatureModel> findNatureForNew(String perId,
			String entId) {
		List<DelegationNatureModel> ret = new ArrayList<DelegationNatureModel>();
		
		List<DelegationNatureModel> cur = findNatureByEntite(entId);
		if (cur.isEmpty() == false) {
			boolean has = false;
			for (DelegationNatureModel delegationNatureModel : cur) {
				has = domDelegationService.hasOtherDelegation(null, delegationNatureModel.getId(), perId, entId);
				if (has == true) {
					continue;
				}
				ret.add(delegationNatureModel);
			}
		}
		
		return ret;
	}

	@Override
	public List<DelegationNatureModel> findNatureForNew(String perId,
			String entId, String ptyId,Boolean isSub) {
		List<DelegationNatureModel> ret = new ArrayList<DelegationNatureModel>();
		
		List<DelegationNatureModel> cur = findNatureByEntiteAndPerimetreType(entId, ptyId,isSub);
		if (cur.isEmpty() == false) {
			boolean has = false;
			for (DelegationNatureModel delegationNatureModel : cur) {
				has = domDelegationService.hasOtherDelegation(null, delegationNatureModel.getId(), perId, entId);
				if (has == true) {
					continue;
				}
				ret.add(delegationNatureModel);
			}
		}
		
		return ret;
	}
}

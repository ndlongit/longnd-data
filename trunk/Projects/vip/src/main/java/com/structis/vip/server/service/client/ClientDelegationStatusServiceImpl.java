package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDelegationStatusService;
import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationStatusService;
import com.structis.vip.shared.model.DelegationStatusModel;

@Service("clientDelegationStatusService")
public class ClientDelegationStatusServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientDelegationStatusService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientDelegationStatusServiceImpl.class);

	@Autowired
	private DomDelegationStatusService domDelegationStatusService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationStatusModel> getAllDelegationStatuses() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationStatusService.getAllDelegationStatuses();
			}
		};
		return (List<DelegationStatusModel>) callManager(callBack);
	}

	@Override
	public DelegationStatusModel findById(Integer id) {
		DelegationStatus delegationStatus = domDelegationStatusService.findById(id);
		return (DelegationStatusModel) modelBeanMapper.map(delegationStatus);
	}

	@Override
	public DelegationStatusModel insert(DelegationStatusModel model) {
		DelegationStatus doc = (DelegationStatus) modelBeanMapper.map(model);
		doc = domDelegationStatusService.insert(doc);
		return (DelegationStatusModel) modelBeanMapper.map(doc);
	}

	@Override
	public DelegationStatusModel update(DelegationStatusModel model) {
		DelegationStatus doc = (DelegationStatus) modelBeanMapper.map(model);
		doc = domDelegationStatusService.update(doc);
		return (DelegationStatusModel) modelBeanMapper.map(doc);
	}

	@Override
	public Boolean delete(DelegationStatusModel model) {
		DelegationStatus dm = (DelegationStatus) modelBeanMapper.map(model);
		domDelegationStatusService.delete(dm);
		return true;
	}
}

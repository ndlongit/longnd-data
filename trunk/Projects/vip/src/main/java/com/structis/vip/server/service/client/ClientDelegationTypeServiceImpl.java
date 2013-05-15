package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDelegationTypeService;
import com.structis.vip.server.bean.domain.DelegationType;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegationTypeService;
import com.structis.vip.shared.exception.DelegationTypeException;
import com.structis.vip.shared.model.DelegationTypeModel;

@Service("clientDelegationTypeService")
public class ClientDelegationTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientDelegationTypeService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientDelegationTypeServiceImpl.class);

	@Autowired
	private DomDelegationTypeService domDelegationTypeService;

	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationTypeModel> getAllTypes() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDelegationTypeService.getAllTypes();
			}
		};
		return (List<DelegationTypeModel>) callManager(callBack);
	}

	@Override
	public DelegationTypeModel getPrincipleType() {
		DelegationType delegationType = domDelegationTypeService.getById(DelegationTypeModel.PRINCIPLE_TYPE);
		return (DelegationTypeModel) modelBeanMapper.map(delegationType);
	}

	@Override
	public DelegationTypeModel getTemporaryType() {
		DelegationType delegationType = domDelegationTypeService.getById(DelegationTypeModel.TEMPORARY_TYPE);
		return (DelegationTypeModel) modelBeanMapper.map(delegationType);
	}

	@Override
	public DelegationTypeModel getSubType() {
		DelegationType delegationType = domDelegationTypeService.getById(DelegationTypeModel.SUB_TYPE);
		return (DelegationTypeModel) modelBeanMapper.map(delegationType);
	}

	@Override
	public DelegationTypeModel getDelegationTypeOf(String type) {
		DelegationType dt = domDelegationTypeService.getByType(type);
		return (DelegationTypeModel) modelBeanMapper.map(dt);
	}

	@Override
	public DelegationTypeModel insert(DelegationTypeModel model) throws DelegationTypeException {
		DelegationType delegationType = (DelegationType) modelBeanMapper.map(model);
		// if (domDelegationTypeService.getByType(model.getName().toLowerCase()) == null) {
		// delegationType = domDelegationTypeService.insert(delegationType);
		// } else {
		// throw new DelegationTypeException(ExceptionType.DELEGATION_TYPE_SAME_NAME);
		// }
		delegationType = domDelegationTypeService.insert(delegationType);
		return (DelegationTypeModel) modelBeanMapper.map(delegationType);
	}

	@Override
	public DelegationTypeModel update(DelegationTypeModel model) {
		DelegationType delegationType = (DelegationType) modelBeanMapper.map(model);
		delegationType = domDelegationTypeService.update(delegationType);
		return (DelegationTypeModel) modelBeanMapper.map(delegationType);
	}
}

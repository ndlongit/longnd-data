package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientExternControllerService;
import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomExternControllerService;
import com.structis.vip.shared.model.ExternControllerModel;

@Service("clientExternControllerService")
public class ClientExternControllerServiceImpl extends DependencyInjectionRemoteServiceServlet implements
ClientExternControllerService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientExternControllerServiceImpl.class);

	@Autowired
	private DomExternControllerService domControlService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	
	@Override
	public Boolean delete(ExternControllerModel model) {
		ExternController dm = (ExternController) modelBeanMapper.map(model);		
		domControlService.delete(dm);
		return true;
	}

	@Override
	public ExternControllerModel insert(ExternControllerModel model) {
		ExternController doc = (ExternController) modelBeanMapper.map(model);
		doc = domControlService.insert(doc);
		return (ExternControllerModel) modelBeanMapper.map(doc);
	}

	@Override
	public ExternControllerModel update(ExternControllerModel model) {
		ExternController doc = (ExternController) modelBeanMapper.map(model);
		doc = domControlService.update(doc);
		return (ExternControllerModel) modelBeanMapper.map(doc);
	}

	@Override
	public List<ExternControllerModel> findAll() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domControlService.findAll();
			}
		};
		return (List<ExternControllerModel>) callManager(callBack);
	}
	
}

package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientExternControllerControlService;
import com.structis.vip.client.service.ClientExternControllerService;
import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomExternControllerControlService;
import com.structis.vip.server.service.domain.DomExternControllerService;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.ExternControllerModel;

@Service("clientExternControllerControlService")
public class ClientExternControllerControlServiceImpl extends DependencyInjectionRemoteServiceServlet implements
ClientExternControllerControlService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientExternControllerControlServiceImpl.class);

	@Autowired
	private DomExternControllerControlService domControlService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	
	@Override
	public Boolean delete(ExtControllerControlModel model) {
		ExtControllerControl dm = (ExtControllerControl) modelBeanMapper.map(model);
		domControlService.delete(dm);
		return true;
	}

	@Override
	public ExtControllerControlModel insert(ExtControllerControlModel model) {
		ExtControllerControl doc = (ExtControllerControl) modelBeanMapper.map(model);
		doc = domControlService.insert(doc);
		return (ExtControllerControlModel) modelBeanMapper.map(doc);
	}

	@Override
	public ExtControllerControlModel update(ExtControllerControlModel model) {
		ExtControllerControl doc = (ExtControllerControl) modelBeanMapper.map(model);
		doc = domControlService.update(doc);
		return (ExtControllerControlModel) modelBeanMapper.map(doc);
	}

	@Override
	public List<ExtControllerControlModel> findAll() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domControlService.findAll();
			}
		};
		return (List<ExtControllerControlModel>) callManager(callBack);
	}

	@Override
	public List<ExtControllerControlModel> findByControl(final Integer id) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domControlService.findByControl(id);
			}
		};
		return (List<ExtControllerControlModel>) callManager(callBack);
	}

	@Override
	public Boolean deleteByControl(Integer control) {
		return domControlService.deleteByControl(control);
	}

	@Override
	public void insert(List<ExtControllerControlModel> eccs) {
		for (ExtControllerControlModel ecc: eccs) {
			insert(ecc);
		}		
	}

	@Override
	public void insert(Integer controlId, List<ExtControllerControlModel> eccs) {
		for (ExtControllerControlModel ecc: eccs) {						
			insert(controlId, ecc);
		}	
	}

	private void insert(Integer controlId, ExtControllerControlModel ecc) {
		ExtControllerControl doc = (ExtControllerControl) modelBeanMapper.map(ecc);
		Control control = new Control();
		control.setId(controlId);
		doc.setControl(control);
		doc = domControlService.insert(doc);		
	}
	
}

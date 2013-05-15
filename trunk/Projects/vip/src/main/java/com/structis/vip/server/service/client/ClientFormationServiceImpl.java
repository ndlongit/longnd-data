package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientFormationService;
import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomFormationService;
import com.structis.vip.shared.model.FormationModel;

@Service("clientFormationService")
public class ClientFormationServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientFormationService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientFormationServiceImpl.class);

	@Autowired
	private DomFormationService domFormationService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<FormationModel> findByEntite(final String entiteId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domFormationService.findByEntite(entiteId);
			}
		};
		return (List<FormationModel>) callManager(callBack);
	}

	@Override
	public Boolean delete(FormationModel model) {
		Formation dm = (Formation) modelBeanMapper.map(model);
		domFormationService.delete(dm);
		return true;
	}

	@Override
	public FormationModel insert(FormationModel model) {
		Formation doc = (Formation) modelBeanMapper.map(model);
		doc = domFormationService.insert(doc);
		return (FormationModel) modelBeanMapper.map(doc);
	}

	@Override
	public FormationModel update(FormationModel model) {
		Formation doc = (Formation) modelBeanMapper.map(model);
		doc = domFormationService.update(doc);
		return (FormationModel) modelBeanMapper.map(doc);
	}

	@Override
	public List<FormationModel> findAll() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domFormationService.findAll();
			}
		};
		return (List<FormationModel>) callManager(callBack);
	}
}

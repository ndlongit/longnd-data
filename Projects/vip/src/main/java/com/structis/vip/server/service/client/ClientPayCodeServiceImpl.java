package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientPayCodeService;
import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomPayCodeService;
import com.structis.vip.shared.model.PayCodeModel;

@Service("clientPayCodeService")
public class ClientPayCodeServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientPayCodeService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientPayCodeServiceImpl.class);

	@Autowired
	private DomPayCodeService domPayCodeService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@Override
	public Boolean delete(PayCodeModel model) {
		PayCode dm = (PayCode) modelBeanMapper.map(model);
		domPayCodeService.delete(dm);
		return true;
	}

	@Override
	public PayCodeModel insert(PayCodeModel model) {
		PayCode doc = (PayCode) modelBeanMapper.map(model);
		doc = domPayCodeService.insert(doc);
		return (PayCodeModel) modelBeanMapper.map(doc);
	}

	@Override
	public PayCodeModel update(PayCodeModel model) {
		PayCode doc = (PayCode) modelBeanMapper.map(model);
		doc = domPayCodeService.update(doc);
		return (PayCodeModel) modelBeanMapper.map(doc);
	}

	@Override
	public List<PayCodeModel> findAll() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domPayCodeService.findAll();
			}
		};
		return (List<PayCodeModel>) callManager(callBack);
	}

	@Override
	public PayCodeModel findByCode(String code) {
		PayCode payCode = domPayCodeService.findByCode(code);
		return (PayCodeModel) modelBeanMapper.map(payCode);
	}
}

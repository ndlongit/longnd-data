package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientChantierTypeService;
import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomChantierTypeService;
import com.structis.vip.server.service.domain.DomPerimetreService;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.ChantierTypeModel;

@Service("clientChantierTypeService")
public class ClientChantierTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientChantierTypeService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientChantierTypeServiceImpl.class);

	@Autowired
	private DomChantierTypeService domChantierTypeService;
	
	@Autowired
	private DomPerimetreService domPerimetreService;
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<ChantierTypeModel> findChantierByEntite(final String entiteId) {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domChantierTypeService.findChantierByEntite(entiteId);
			}
		};
		return (List<ChantierTypeModel>) callManager(callBack);
	}

	@Override
	public Boolean delete(ChantierTypeModel model) {
		List<Perimetre> perimetres = domPerimetreService.findByChantierTypeId(model.getId());
		if (perimetres.isEmpty() == false) {
			throw new ChantierTypeException(ExceptionType.CHANTIER_TYPE_DELETE_EIXST);
		}
		
		ChantierType dm = (ChantierType) modelBeanMapper.map(model);
		domChantierTypeService.delete(dm);
		
		return true;
	}

	@Override
	public ChantierTypeModel insert(ChantierTypeModel model) {
		ChantierType doc = (ChantierType) modelBeanMapper.map(model);
		doc = domChantierTypeService.insert(doc);
		return (ChantierTypeModel) modelBeanMapper.map(doc);
	}

	@Override
	public ChantierTypeModel update(ChantierTypeModel model) {
		ChantierType doc = (ChantierType) modelBeanMapper.map(model);
		doc = domChantierTypeService.update(doc);
		return (ChantierTypeModel) modelBeanMapper.map(doc);
	}

	@Override
	public List<ChantierTypeModel> findAll() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domChantierTypeService.findAll();
			}
		};
		return (List<ChantierTypeModel>) callManager(callBack);
	}
}

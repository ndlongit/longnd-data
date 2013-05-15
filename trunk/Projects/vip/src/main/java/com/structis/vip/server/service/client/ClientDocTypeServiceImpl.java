package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDocTypeService;
import com.structis.vip.server.bean.domain.DocumentType;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDocTypeService;
import com.structis.vip.shared.model.DocumentTypeModel;

@Service("clientDocTypeService")
public class ClientDocTypeServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientDocTypeService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientDocTypeServiceImpl.class);
	
	@Autowired
	private DomDocTypeService domDocTypeService; 
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentTypeModel> getDocTypes() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domDocTypeService.find();
			}
		};
		return (List<DocumentTypeModel>) callManager(callBack);
	}

	@Override
	public DocumentTypeModel findById(Integer DocTypeId) {
		DocumentType lg = domDocTypeService.getByPrimaryKey(DocTypeId);
		return (DocumentTypeModel) modelBeanMapper.map(lg);
	}

	@Override
	public Boolean delete(DocumentTypeModel model) {	
		DocumentType dm = (DocumentType) modelBeanMapper.map(model);
		domDocTypeService.delete(dm);
		return true;
	}

	@Override
	public DocumentTypeModel insert(DocumentTypeModel model) {
		DocumentType doc = (DocumentType) modelBeanMapper.map(model);
		doc = domDocTypeService.insert(doc);
		return (DocumentTypeModel) modelBeanMapper.map(doc);
	}

	@Override
	public DocumentTypeModel update(DocumentTypeModel model) {
		DocumentType doc = (DocumentType) modelBeanMapper.map(model);
		doc = domDocTypeService.update(doc);
		return (DocumentTypeModel) modelBeanMapper.map(doc);
	}
	
}

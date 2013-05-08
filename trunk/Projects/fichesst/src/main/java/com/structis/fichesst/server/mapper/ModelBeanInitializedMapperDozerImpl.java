package com.structis.fichesst.server.mapper;

import org.dozer.CustomFieldMapper;

public class ModelBeanInitializedMapperDozerImpl implements ModelBeanInitializedMapperDozer {
	private CustomFieldMapper dozerCustomFieldMapper;

	private ModelBeanMapperIfc modelBeanMapper;

	/*
	 * public ModelBeanInitializedMapperDozerImpl(){ getMapper().setCustomFieldMapper(dozerCustomFieldMapper); }
	 */

	public void setDozerCustomFieldMapper(CustomFieldMapper dozerCustomFieldMapper) {
		this.dozerCustomFieldMapper = dozerCustomFieldMapper;
	}

	public void setModelBeanMapper(ModelBeanMapperIfc modelBeanMapper) {
		this.modelBeanMapper = modelBeanMapper;
	}

	@Override
	public Object map(Object object) {
		modelBeanMapper.getMapper().setCustomFieldMapper(dozerCustomFieldMapper);
		Object ob = modelBeanMapper.map(object);
		modelBeanMapper.getMapper().setCustomFieldMapper(null);
		return ob;
	}

	@Override
	public Object map(Object object, String mapId) {
		modelBeanMapper.getMapper().setCustomFieldMapper(dozerCustomFieldMapper);
		Object ob = modelBeanMapper.map(object, mapId);
		modelBeanMapper.getMapper().setCustomFieldMapper(null);
		return ob;
	}
}

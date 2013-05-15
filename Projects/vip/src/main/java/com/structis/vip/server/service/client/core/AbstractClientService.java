package com.structis.vip.server.service.client.core;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractClientService<BaseClass, ModelClass> {

	private boolean initClasses = false;
	private Class<ModelClass> modelClass = null;
	private Class<BaseClass> baseClass = null;
	
	@Autowired
	private DozerBeanMapper dozerMapper;
	
	protected ModelClass mapToDto(BaseClass source) {
		initClasses();
		return dozerMapper.map(source, modelClass);
	}
	
	protected List<ModelClass> mapListToDto(List<BaseClass> sources) {
		initClasses();
		List<ModelClass> models = new ArrayList<ModelClass>();
		for(BaseClass source : sources) {
			models.add(dozerMapper.map(source, modelClass));
		}
		return models;
	}

	protected BaseClass mapToBase(ModelClass source) {
		initClasses();
		return dozerMapper.map(source,  baseClass);
	}
	
	protected void merge(ModelClass source, BaseClass destination){
		initClasses();
		dozerMapper.map(source, destination);
	}
	
	protected List<BaseClass> mapListToBase(List<ModelClass> models) {
		initClasses();
		List<BaseClass> sources = new ArrayList<BaseClass>();
		for(ModelClass model : models) {
			sources.add(dozerMapper.map(model, baseClass));
		}
		return sources;
	}
	
	protected DozerBeanMapper getDozerBeanMapper() {
		return dozerMapper;
	}

	@SuppressWarnings("unchecked")
	private void initClasses() {
		if(!initClasses) {
			initClasses = !initClasses;
			baseClass = (Class<BaseClass>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
			modelClass = (Class<ModelClass>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[1];
		}
	}
		
}

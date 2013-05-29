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
        this.initClasses();
        return this.dozerMapper.map(source, this.modelClass);
    }

    protected List<ModelClass> mapListToDto(List<BaseClass> sources) {
        this.initClasses();
        List<ModelClass> models = new ArrayList<ModelClass>();
        for (BaseClass source : sources) {
            models.add(this.dozerMapper.map(source, this.modelClass));
        }
        return models;
    }

    protected BaseClass mapToBase(ModelClass source) {
        this.initClasses();
        return this.dozerMapper.map(source, this.baseClass);
    }

    protected void merge(ModelClass source, BaseClass destination) {
        this.initClasses();
        this.dozerMapper.map(source, destination);
    }

    protected List<BaseClass> mapListToBase(List<ModelClass> models) {
        this.initClasses();
        List<BaseClass> sources = new ArrayList<BaseClass>();
        for (ModelClass model : models) {
            sources.add(this.dozerMapper.map(model, this.baseClass));
        }
        return sources;
    }

    protected DozerBeanMapper getDozerBeanMapper() {
        return this.dozerMapper;
    }

    @SuppressWarnings("unchecked")
    private void initClasses() {
        if (!this.initClasses) {
            this.initClasses = !this.initClasses;
            this.baseClass = (Class<BaseClass>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            this.modelClass = (Class<ModelClass>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

}

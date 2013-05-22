package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientControlTypeService;
import com.structis.vip.server.bean.domain.ControlType;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomControlTypeService;
import com.structis.vip.shared.model.ControlTypeModel;

@Service("clientControlTypeService")
public class ClientControlTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientControlTypeService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientControlTypeServiceImpl.class);

    @Autowired
    private DomControlTypeService domControlTypeService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlTypeModel> findByEntite(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientControlTypeServiceImpl.this.domControlTypeService.findByEntite(entiteId);
            }
        };
        return (List<ControlTypeModel>) this.callManager(callBack);
    }

    @Override
    public Boolean delete(ControlTypeModel model) {
        ControlType dm = (ControlType) this.modelBeanMapper.map(model);
        this.domControlTypeService.delete(dm);
        return true;
    }

    @Override
    public ControlTypeModel insert(ControlTypeModel model) {
        ControlType doc = (ControlType) this.modelBeanMapper.map(model);
        doc = this.domControlTypeService.insert(doc);
        return (ControlTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public ControlTypeModel update(ControlTypeModel model) {
        ControlType doc = (ControlType) this.modelBeanMapper.map(model);
        doc = this.domControlTypeService.update(doc);
        return (ControlTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public List<ControlTypeModel> findAll() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientControlTypeServiceImpl.this.domControlTypeService.findAll();
            }
        };
        return (List<ControlTypeModel>) this.callManager(callBack);
    }
}

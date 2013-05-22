package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientExternControllerControlService;
import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomExternControllerControlService;
import com.structis.vip.shared.model.ExtControllerControlModel;

@Service("clientExternControllerControlService")
public class ClientExternControllerControlServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientExternControllerControlService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientExternControllerControlServiceImpl.class);

    @Autowired
    private DomExternControllerControlService domControlService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public Boolean delete(ExtControllerControlModel model) {
        ExtControllerControl dm = (ExtControllerControl) this.modelBeanMapper.map(model);
        this.domControlService.delete(dm);
        return true;
    }

    @Override
    public ExtControllerControlModel insert(ExtControllerControlModel model) {
        ExtControllerControl doc = (ExtControllerControl) this.modelBeanMapper.map(model);
        doc = this.domControlService.insert(doc);
        return (ExtControllerControlModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public ExtControllerControlModel update(ExtControllerControlModel model) {
        ExtControllerControl doc = (ExtControllerControl) this.modelBeanMapper.map(model);
        doc = this.domControlService.update(doc);
        return (ExtControllerControlModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public List<ExtControllerControlModel> findAll() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientExternControllerControlServiceImpl.this.domControlService.findAll();
            }
        };
        return (List<ExtControllerControlModel>) this.callManager(callBack);
    }

    @Override
    public List<ExtControllerControlModel> findByControl(final Integer id) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientExternControllerControlServiceImpl.this.domControlService.findByControl(id);
            }
        };
        return (List<ExtControllerControlModel>) this.callManager(callBack);
    }

    @Override
    public Boolean deleteByControl(Integer control) {
        return this.domControlService.deleteByControl(control);
    }

    @Override
    public void insert(List<ExtControllerControlModel> eccs) {
        for (ExtControllerControlModel ecc : eccs) {
            this.insert(ecc);
        }
    }

    @Override
    public void insert(Integer controlId, List<ExtControllerControlModel> eccs) {
        for (ExtControllerControlModel ecc : eccs) {
            this.insert(controlId, ecc);
        }
    }

    private void insert(Integer controlId, ExtControllerControlModel ecc) {
        ExtControllerControl doc = (ExtControllerControl) this.modelBeanMapper.map(ecc);
        Control control = new Control();
        control.setId(controlId);
        doc.setControl(control);
        doc = this.domControlService.insert(doc);
    }

}

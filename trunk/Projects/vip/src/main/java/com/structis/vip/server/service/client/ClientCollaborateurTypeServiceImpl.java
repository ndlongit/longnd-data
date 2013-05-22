package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientCollaborateurTypeService;
import com.structis.vip.server.bean.domain.CollaborateurType;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomCollaborateurTypeService;
import com.structis.vip.shared.model.CollaborateurTypeModel;

@Service("clientCollaborateurTypeService")
public class ClientCollaborateurTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientCollaborateurTypeService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientCollaborateurTypeServiceImpl.class);

    @Autowired
    private DomCollaborateurTypeService domCollaborateurTypeService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurTypeModel> getAllCollaborateurType() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurTypeServiceImpl.this.domCollaborateurTypeService.getAllCollaborateurType();
            }
        };
        return (List<CollaborateurTypeModel>) this.callManager(callBack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CollaborateurTypeModel> getCollaborateurTypeByEntite(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientCollaborateurTypeServiceImpl.this.domCollaborateurTypeService.getCollaborateurTypeByEntite(entiteId);
            }
        };
        return (List<CollaborateurTypeModel>) this.callManager(callBack);
    }

    @Override
    public Boolean delete(CollaborateurTypeModel model) {
        CollaborateurType dm = (CollaborateurType) this.modelBeanMapper.map(model);
        this.domCollaborateurTypeService.delete(dm);
        return true;
    }

    @Override
    public CollaborateurTypeModel insert(CollaborateurTypeModel model) {
        CollaborateurType doc = (CollaborateurType) this.modelBeanMapper.map(model);
        doc = this.domCollaborateurTypeService.insert(doc);
        return (CollaborateurTypeModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public CollaborateurTypeModel update(CollaborateurTypeModel model) {
        CollaborateurType doc = (CollaborateurType) this.modelBeanMapper.map(model);
        doc = this.domCollaborateurTypeService.update(doc);
        return (CollaborateurTypeModel) this.modelBeanMapper.map(doc);
    }

}

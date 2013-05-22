package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientFieldService;
import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomFieldService;
import com.structis.vip.shared.model.FieFieldModel;

@Service("clientFieldService")
public class ClientFieldServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientFieldService {

    private static final long serialVersionUID = 1L;
    @Autowired
    private DomFieldService domFieldService;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientFieldServiceImpl.class);

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public List<FieFieldModel> getFieldsByEntiteId(final String entiteId) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientFieldServiceImpl.this.domFieldService.getFieldsByEntiteId(entiteId);
            }
        };
        return (List<FieFieldModel>) this.callManager(callBack);
    }

    @Override
    public FieFieldModel insert(FieFieldModel fieFieldModel) {
        FieField fieField = (FieField) this.modelBeanMapper.map(fieFieldModel);
        fieField = this.domFieldService.insert(fieField);
        return (FieFieldModel) this.modelBeanMapper.map(fieField);
    }

    @Override
    public List<FieFieldModel> getFieldsByGroupName(final String entId, final String groupName) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientFieldServiceImpl.this.domFieldService.getFieldsByGroupName(entId, groupName);
            }
        };
        return (List<FieFieldModel>) this.callManager(callBack);
    }
}

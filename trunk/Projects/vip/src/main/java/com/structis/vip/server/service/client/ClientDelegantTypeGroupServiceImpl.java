package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDelegantTypeGroupService;
import com.structis.vip.server.bean.domain.DelegantTypeGroup;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDelegantTypeGroupService;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

@Service("clientDelegantTypeGroupService")
public class ClientDelegantTypeGroupServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientDelegantTypeGroupService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientDelegantTypeGroupServiceImpl.class);

    @Autowired
    private DomDelegantTypeGroupService domDelegantTypeGroupService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<DelegantTypeGroupModel> getDelegantTypeGroups() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDelegantTypeGroupServiceImpl.this.domDelegantTypeGroupService.find();
            }
        };
        return (List<DelegantTypeGroupModel>) this.callManager(callBack);
    }

    @Override
    public DelegantTypeGroupModel findById(Integer delegantTypeGroupId) {
        DelegantTypeGroup lg = this.domDelegantTypeGroupService.getByPrimaryKey(delegantTypeGroupId);
        return (DelegantTypeGroupModel) this.modelBeanMapper.map(lg);
    }

    @Override
    public Boolean delete(DelegantTypeGroupModel model) {
        DelegantTypeGroup dm = (DelegantTypeGroup) this.modelBeanMapper.map(model);
        this.domDelegantTypeGroupService.delete(dm);
        return true;
    }

    @Override
    public DelegantTypeGroupModel insert(DelegantTypeGroupModel model) {
        DelegantTypeGroup doc = (DelegantTypeGroup) this.modelBeanMapper.map(model);
        doc = this.domDelegantTypeGroupService.insert(doc);
        return (DelegantTypeGroupModel) this.modelBeanMapper.map(doc);
    }

    @Override
    public DelegantTypeGroupModel update(DelegantTypeGroupModel model) {
        DelegantTypeGroup doc = (DelegantTypeGroup) this.modelBeanMapper.map(model);
        doc = this.domDelegantTypeGroupService.update(doc);
        return (DelegantTypeGroupModel) this.modelBeanMapper.map(doc);
    }

}

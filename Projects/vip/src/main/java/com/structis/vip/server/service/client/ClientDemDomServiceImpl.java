package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDemDomService;
import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomDemDomService;
import com.structis.vip.shared.model.DemDomModel;

@Service("clientDemDomService")
public class ClientDemDomServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientDemDomService {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientDemDomServiceImpl.class);

    @Autowired
    private DomDemDomService domDemDomService;

    @Autowired
    ModelBeanMapperIfc modelBeanMapper;

    @Override
    public List<DemDomModel> getAllDemDomsByDemGroup(final Integer group) {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDemDomServiceImpl.this.domDemDomService.getAllDemDomsByDemGroup(group);
            }
        };
        return (List<DemDomModel>) this.callManager(callBack);
    }

    @Override
    public DemDomModel insert(DemDomModel demDom) {
        DemDom createDemDom = (DemDom) this.modelBeanMapper.map(demDom);
        createDemDom = this.domDemDomService.insert(createDemDom);
        return (DemDomModel) this.modelBeanMapper.map(createDemDom);
    }

    @Override
    public Integer insert(List<DemDomModel> demDoms, Integer group) {
        for (DemDomModel demDom : demDoms) {
            demDom.setId(null);
            demDom.setGroup(group);
            this.insert(demDom);
        }
        return group;
    }

    @Override
    public Boolean deleteByGroup(Integer group) {
        return this.domDemDomService.deleteByGroup(group);
    }
}
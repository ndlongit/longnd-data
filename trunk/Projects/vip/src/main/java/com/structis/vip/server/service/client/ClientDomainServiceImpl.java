package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientDomainService;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.service.domain.DomDomainService;
import com.structis.vip.shared.model.DomainModel;

@Service("clientDomainService")
public class ClientDomainServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientDomainService {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ClientDomainServiceImpl.class);

    @Autowired
    private DomDomainService domDomainService;

    @SuppressWarnings("unchecked")
    @Override
    public List<DomainModel> getDomains() {
        ManagerCallBack callBack = new ManagerCallBack() {

            @Override
            public Object execute(Object... inputs) {
                return ClientDomainServiceImpl.this.domDomainService.getDomains();
            }
        };
        return (List<DomainModel>) this.callManager(callBack);
    }
}

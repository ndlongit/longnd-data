package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientConductorService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.service.domain.ConductorService;

@Service("clientConductorService")
public class ClientConductorServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientConductorService {
	@Autowired
	private ConductorService conductorService;
}

package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientPenaltyService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;

@Service("clientPenaltyService")
public class ClientPenaltyServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientPenaltyService {
//	@Autowired
//	private PenaltyService penaltyService;
}

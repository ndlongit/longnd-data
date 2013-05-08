package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientLotService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.service.domain.LotService;

@Service("clientLotService")
public class ClientLotServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientLotService {
	@Autowired
	private LotService lotService;
}

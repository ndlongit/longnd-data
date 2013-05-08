package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientProgressService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.service.domain.ProgressService;

@Service("clientProgressService")
public class ClientProgressServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientProgressService {
	@Autowired
	private ProgressService progressService;
}

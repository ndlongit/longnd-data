package com.structis.fichesst.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientDeductionService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.service.domain.DeductionService;

@Service("clientDeductionService")
public class ClientDeductionServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientDeductionService {
	@Autowired
	private DeductionService deductionService;
}

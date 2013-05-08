package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefModePaiementService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.RefModePaiementService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientRefModePaiementService")
public class ClientRefModePaiementServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientRefModePaiementService {
	@Autowired
	private RefModePaiementService refModePaiementService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return refModePaiementService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}

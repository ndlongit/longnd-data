package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefDdeAgrementService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.RefDdeAgrementService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientRefDdeAgrementService")
public class ClientRefDdeAgrementServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientRefDdeAgrementService {
	@Autowired
	private RefDdeAgrementService refDdeAgrementService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return refDdeAgrementService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}

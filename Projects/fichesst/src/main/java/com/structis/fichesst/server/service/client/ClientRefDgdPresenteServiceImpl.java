package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefDgdPresenteService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.RefDgdPresenteService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientRefDgdPresenteService")
public class ClientRefDgdPresenteServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientRefDgdPresenteService {
	
	@Autowired
	private RefDgdPresenteService refDgdPresenteService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return refDgdPresenteService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}
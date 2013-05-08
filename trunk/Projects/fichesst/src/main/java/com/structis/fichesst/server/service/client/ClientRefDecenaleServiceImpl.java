package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefDecenaleService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.RefDecenaleService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientRefDecenaleService")
public class ClientRefDecenaleServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientRefDecenaleService {
	@Autowired
	private RefDecenaleService refDecenaleService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return refDecenaleService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}

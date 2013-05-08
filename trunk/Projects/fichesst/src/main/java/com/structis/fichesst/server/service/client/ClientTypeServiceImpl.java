package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientTypeService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.TypeService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientTypeService")
public class ClientTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientTypeService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TypeService typeService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return typeService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}

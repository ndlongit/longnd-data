package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientStatusService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.StatusService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientStatusService")
public class ClientStatusServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientStatusService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StatusService statusService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return statusService.findAll();
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}
}

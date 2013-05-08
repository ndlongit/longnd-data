package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientLotTypeService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.LotTypeService;
import com.structis.fichesst.shared.dto.LotTypeDto;

@Service("clientLotTypeService")
public class ClientLotTypeServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientLotTypeService {
	
	@Autowired
	private LotTypeService lotTypeService;

	@SuppressWarnings("unchecked")
	@Override
	public List<LotTypeDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return lotTypeService.findAll();
			}
		};

		List<LotTypeDto> finalList = (List<LotTypeDto>) callManager(manager);

		return finalList;
	}
}

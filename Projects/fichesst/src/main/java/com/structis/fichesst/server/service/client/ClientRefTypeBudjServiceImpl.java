package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefTypeBudjService;
import com.structis.fichesst.server.bean.domain.RefTypeBudjConf;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.DomRefTypeBudjService;
import com.structis.fichesst.shared.dto.SimpleDto;

@Service("clientRefTypeBudjService")
public class ClientRefTypeBudjServiceImpl extends
		DependencyInjectionRemoteServiceServlet implements
		ClientRefTypeBudjService {
	@Autowired
	DomRefTypeBudjService domRefTypeService;

	@Override
	public List<SimpleDto> findAll() {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<RefTypeBudjConf> findAll = domRefTypeService.findAll();
				return findAll;
			}
		};

		List<SimpleDto> finalList = (List<SimpleDto>) callManager(manager);

		return finalList;
	}

}

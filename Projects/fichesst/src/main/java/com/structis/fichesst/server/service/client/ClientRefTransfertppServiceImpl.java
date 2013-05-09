package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientRefTransfertppService;
import com.structis.fichesst.server.bean.domain.RefTransfertPP;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.RefTransfertppService;
import com.structis.fichesst.shared.dto.RefTransfertPPDto;

@Service("clientRefTransfertppService")
public class ClientRefTransfertppServiceImpl extends DependencyInjectionRemoteServiceServlet implements
		ClientRefTransfertppService {

	@Autowired
	private RefTransfertppService refTransfertppService;

	@Override
	public RefTransfertPPDto findById(final Integer id) {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) throws Exception {
				RefTransfertPP ref = refTransfertppService.find(id);
				return ref;
			}
		};
		RefTransfertPPDto simple = (RefTransfertPPDto) callManager(manager, id);
		return simple;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefTransfertPPDto> findAll() {

		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<RefTransfertPP> results = refTransfertppService.findAll();
				return results;
			}
		};

		List<RefTransfertPPDto> finalList = (List<RefTransfertPPDto>) callManager(manager);
		return finalList;
	}
}

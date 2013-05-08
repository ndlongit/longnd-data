package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientGestionService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.GestionService;
import com.structis.fichesst.shared.dto.GestionDto;

@Service("clientGestionService")
public class ClientGestionServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientGestionService {
	@Autowired
	private GestionService gestionService;

	@SuppressWarnings("unchecked")
	@Override
	public List<GestionDto> findGestionList(Integer ficheStId) {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return gestionService.find((Integer) inputs[0]);
			}
		};
		List<GestionDto> results = (List<GestionDto>) callManager(manager, ficheStId);

		return results;
	}

	@Override
	public Integer saveGestionList(List<GestionDto> gestionList, Integer ficheStId) {
		ManagerCallBack manager = new ManagerCallBack() {

			@SuppressWarnings("unchecked")
			@Override
			public Object execute(Object... inputs) {
				return gestionService.save((List<GestionDto>) inputs[0], (Integer) inputs[1]);
			}
		};
		List<GestionDto> results = (List<GestionDto>) callManager(manager, ficheStId);

		return results.size();
	}
}

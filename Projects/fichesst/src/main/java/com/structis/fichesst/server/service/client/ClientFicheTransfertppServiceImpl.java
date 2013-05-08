package com.structis.fichesst.server.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.structis.fichesst.client.service.ClientFicheTransfertppService;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.FicheTransfertppService;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

@Service("clientFicheTransfertppService")
public class ClientFicheTransfertppServiceImpl extends
		DependencyInjectionRemoteServiceServlet implements
		ClientFicheTransfertppService {
	@Autowired
	private FicheTransfertppService ficheTransfertppService;

	@Override
	public List<FicheTransfertppDto> findAll() {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				List<FicheTransfertpp> findAll = ficheTransfertppService
						.findAll();
				return findAll;
			}
		};

		List<FicheTransfertppDto> finalList = (List<FicheTransfertppDto>) callManager(manager);

		return finalList;
	}

	@SuppressWarnings("unchecked")
    @Override
    public List<FicheTransfertppDto> findByChantierId(Integer chantierId) {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) {
				List<FicheTransfertpp> results = ficheTransfertppService.findByChantierId((Integer)inputs[0]);
				return results;
			}
		};
		List<FicheTransfertppDto> finalList = (List<FicheTransfertppDto>) callManager(manager, chantierId);

		return finalList;
    }

}

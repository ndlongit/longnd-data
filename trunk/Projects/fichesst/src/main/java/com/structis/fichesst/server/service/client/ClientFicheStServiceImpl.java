package com.structis.fichesst.server.service.client;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientFicheStService;
import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.core.ManagerCallBack;
import com.structis.fichesst.server.service.domain.FicheStService;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;

@Service("clientFicheStService")
public class ClientFicheStServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientFicheStService {

	@Autowired
	private FicheStService ficheStService;
	protected Locale localtion = Locale.FRENCH;

	@Override
	public FicheStDto find(Integer id) {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					FicheSt ficheSt = ficheStService.find((Integer) inputs[0]);
					return ficheSt;
				} catch (Exception e) {
					throw e;
				}
			}
		};

		return (FicheStDto) callManager(manager, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FicheStDto> findByChantierId(Integer chantierId) {
		ManagerCallBack manager = new ManagerCallBack() {

			@Override
			public Object execute(Object... inputs) {
				return ficheStService.findByChantierId((Integer) inputs[0]);
			}
		};

		List<FicheStDto> finalList = (List<FicheStDto>) callManager(manager, chantierId);
		return finalList;
	}

	@Override
	public FicheStDto save(FicheStDto model) {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					return ficheStService.save((FicheSt) inputs[0]);
				} catch (Exception e) {
					throw e;
				}
			}
		};

		return (FicheStDto) callManager(manager, model);
	}

	@Override
	public FicheStDto update(FicheStDto model) {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					return ficheStService.update((FicheSt) inputs[0]);
				} catch (Exception e) {
					throw e;
				}
			}
		};

		return (FicheStDto) callManager(manager, model);
	}

	@Override
	public void delete(Integer id) {
		ManagerCallBack manager = new ManagerCallBack() {
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					ficheStService.delete((Integer) inputs[0]);
					return null;
				} catch (Exception e) {
					throw e;
				}
			}
		};

		callManager(manager, id);
	}

	@Override
	public void updateSynthese(ChantierModel chantier, List<FicheStDto> ficheStList, List<FicheTransfertppDto> transfertPpList) {
		ManagerCallBack manager = new ManagerCallBack() {
			@SuppressWarnings("unchecked")
			@Override
			public Object execute(Object... inputs) throws Exception {
				try {
					return ficheStService.updateSynthese((Chantier) inputs[0], (List<FicheSt>) inputs[1], (List<FicheTransfertpp>) inputs[2]);
				} catch (Exception e) {
					throw e;
				}
			}
		};

		callManager(manager, chantier, ficheStList, transfertPpList);
		transfertPpList.get(0).getChantier();
	}

}

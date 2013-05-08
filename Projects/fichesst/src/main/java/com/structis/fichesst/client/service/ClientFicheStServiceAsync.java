package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientFicheStServiceAsync {
	
	public static class Util {

		private static ClientFicheStServiceAsync instance = GWT.create(ClientFicheStService.class);

		public static ClientFicheStServiceAsync getInstance() {
			return instance;
		}
	}

	public void find(Integer id, AsyncCallback<FicheStDto> callback);
	
	public void findByChantierId(Integer chantierId, AsyncCallback<List<FicheStDto>> callback);

	public void save(FicheStDto model, AsyncCallback<FicheStDto> callback);

	public void update(FicheStDto model, AsyncCallback<FicheStDto> callback);
	
	public void delete(Integer id, AsyncCallback<Void> callback);

	void updateSynthese(ChantierModel chantier, List<FicheStDto> ficheStList, List<FicheTransfertppDto> transfertPpList, AsyncCallback<Void> callback);
}

package com.structis.fichesst.client.service;

import java.util.List;

import com.structis.fichesst.shared.dto.LigTransfertppModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientLigTransfertppServiceAsync {
	void findByID(Integer idTransfertpp, Integer idChantier, AsyncCallback<List<LigTransfertppModel>> callback);

	void saveAllLig(List<LigTransfertppModel> ligModels, Integer idChantier, Integer idTransfertpp, AsyncCallback<Void> callback);

	void deleteLig(List<LigTransfertppModel> ligModels, Integer idChantier, Integer idTransfertpp, AsyncCallback<Void> callback);

	void sumByType(AsyncCallback<LigTransfertppModel> callback);
	
}

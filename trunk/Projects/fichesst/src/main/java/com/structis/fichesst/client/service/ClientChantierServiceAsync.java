package com.structis.fichesst.client.service;


import java.util.List;

import com.structis.fichesst.shared.dto.ChantierModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
public interface ClientChantierServiceAsync {
	//get all chantier
	void findAll(AsyncCallback<List<ChantierModel>> callback);
	void createChantier(ChantierModel chantierModel,Integer idUser, AsyncCallback<Boolean> callback);
	void deleteChantier(Integer idChantier, AsyncCallback<Void> callback);
	void findChantierByUser(Integer idUser, AsyncCallback<List<ChantierModel>> callback);
	void findChantierById(Integer idChantier, AsyncCallback<ChantierModel> callback);
	
}

package com.structis.fichesst.client.service;

import java.util.List;

import com.structis.fichesst.shared.dto.GestionDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientGestionServiceAsync {

	void findGestionList(Integer ficheStId, AsyncCallback<List<GestionDto>> callback);

	void saveGestionList(List<GestionDto> gestionList, Integer ficheStId, AsyncCallback<Integer> callback);
}

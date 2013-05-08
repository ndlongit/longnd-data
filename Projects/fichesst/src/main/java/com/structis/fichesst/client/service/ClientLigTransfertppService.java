package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.LigTransfertppModel;

@RemoteServiceRelativePath("springGwtServices/clientLigTransfertppService")
public interface ClientLigTransfertppService extends RemoteService {
	List<LigTransfertppModel> findByID(Integer idTransfertpp, Integer idChantier);

	void saveAllLig(List<LigTransfertppModel> ligModels, Integer idChantier, Integer idTransfertpp);

	void deleteLig(List<LigTransfertppModel> ligModels, Integer idChantier, Integer idTransfertpp);

	LigTransfertppModel sumByType();
	
}

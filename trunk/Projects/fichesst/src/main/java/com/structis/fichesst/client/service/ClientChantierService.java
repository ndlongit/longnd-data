package com.structis.fichesst.client.service;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.ChantierModel;
@RemoteServiceRelativePath("springGwtServices/clientChantierService")
public interface ClientChantierService extends RemoteService {
	//get all chantier
	List<ChantierModel> findAll();
	boolean createChantier(ChantierModel chantierModel,Integer idUser);
	void deleteChantier(Integer idChantier);
	List<ChantierModel> findChantierByUser(Integer idUser);
	ChantierModel findChantierById(Integer idChantier);
	
}

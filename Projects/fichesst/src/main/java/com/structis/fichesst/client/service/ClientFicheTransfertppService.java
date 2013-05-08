package com.structis.fichesst.client.service;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;
@RemoteServiceRelativePath("springGwtServices/clientFicheTransfertppService")
public interface ClientFicheTransfertppService extends RemoteService {
	List<FicheTransfertppDto> findAll();

	List<FicheTransfertppDto> findByChantierId(Integer chantierId);
	
}

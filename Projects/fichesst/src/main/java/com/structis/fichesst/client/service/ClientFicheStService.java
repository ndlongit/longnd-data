package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.FicheTransfertppDto;

@RemoteServiceRelativePath("springGwtServices/clientFicheStService")
public interface ClientFicheStService extends RemoteService {

	public FicheStDto find(Integer id);
	
	public List<FicheStDto> findByChantierId(Integer chantierId);

	public FicheStDto save(FicheStDto model);

	public FicheStDto update(FicheStDto model);
	
	public void delete(Integer id);

	void updateSynthese(ChantierModel chantier, List<FicheStDto> ficheStList, List<FicheTransfertppDto> transfertPpList);
}

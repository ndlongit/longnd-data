package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.GestionDto;

@RemoteServiceRelativePath("springGwtServices/clientGestionService")
public interface ClientGestionService extends RemoteService {

	List<GestionDto> findGestionList(Integer ficheStId);
}

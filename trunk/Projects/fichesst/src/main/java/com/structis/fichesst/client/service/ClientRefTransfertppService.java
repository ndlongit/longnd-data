package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.SimpleDto;

@RemoteServiceRelativePath("springGwtServices/clientRefTransfertppService")
public interface ClientRefTransfertppService extends RemoteService {

	SimpleDto findById(Integer id);

	List<SimpleDto> findAll();
}

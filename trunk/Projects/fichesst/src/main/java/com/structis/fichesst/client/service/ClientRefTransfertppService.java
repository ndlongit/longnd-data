package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.RefTransfertPPDto;

@RemoteServiceRelativePath("springGwtServices/clientRefTransfertppService")
public interface ClientRefTransfertppService extends RemoteService {

	RefTransfertPPDto findById(Integer id);

	List<RefTransfertPPDto> findAll();
}

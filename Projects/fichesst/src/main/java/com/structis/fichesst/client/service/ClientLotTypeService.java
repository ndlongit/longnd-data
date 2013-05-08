package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.LotTypeDto;

@RemoteServiceRelativePath("springGwtServices/clientLotTypeService")
public interface ClientLotTypeService extends RemoteService {
	List<LotTypeDto> findAll();
}

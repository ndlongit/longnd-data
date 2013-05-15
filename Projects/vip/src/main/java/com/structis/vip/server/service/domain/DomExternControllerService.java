package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomExternControllerService extends GenericEntityService<ExternController, Integer> {
	
	ExternController insert(ExternController doc);

	ExternController update(ExternController doc);

	List<ExternController> findAll();

	List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId);	
}

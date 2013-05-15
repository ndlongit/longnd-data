package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegantTypeGroup;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDelegantTypeGroupService extends GenericEntityService<DelegantTypeGroup, Integer> {
	List<DelegantTypeGroup> getDelegantTypeGroups();
	DelegantTypeGroup findById(Integer catId);
	DelegantTypeGroup insert(DelegantTypeGroup doc);
	DelegantTypeGroup update(DelegantTypeGroup doc);	
	void delete(DelegantTypeGroup doc);
}

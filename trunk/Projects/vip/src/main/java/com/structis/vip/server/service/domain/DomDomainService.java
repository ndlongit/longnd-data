package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Domain;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDomainService extends GenericEntityService<Domain, Integer> {
	List<Domain> getDomains();
}

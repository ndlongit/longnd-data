package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.ControlType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomControlTypeService extends GenericEntityService<ControlType, Integer> {
	List<ControlType> findByEntite(String entiteId);

	ControlType insert(ControlType doc);

	ControlType update(ControlType doc);

	List<ControlType> findAll();
}

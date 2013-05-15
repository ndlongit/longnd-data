package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomFieldService extends GenericEntityService<FieField, Integer> {
	List<FieField> getFieldsByEntiteId(String entiteId);
	FieField insert(FieField fieField);
	List<FieField> getFieldsByGroupName(String entId, String groupName);
	List<FieField> findByLanguageId(Integer language);
	List<FieField> findByEntite(String entId);
}

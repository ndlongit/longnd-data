package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomFieldRuleService extends GenericEntityService<FieldRule, Integer> {

	List<FieldRule> getRulesByDemGroup(Integer group);

	List<FieldRule> getRules(String entId, String ptyId, Integer dnaId, Integer langId);

	Boolean update(FieldRule fieldRule);
	
	FieldRule insert(FieldRule fieldRule);
	
	Boolean deleteByGroup(Integer group);
}

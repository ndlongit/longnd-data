package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.dao.support.GenericDao;

public interface FieldRuleDao extends GenericDao<FieldRule, Integer> {

	List<FieldRule> getRulesByDemGroup(Integer group);

	List<FieldRule> getRules(String entId, String ptyId, Integer dnaId, Integer langId);

	FieldRule update(FieldRule fieldRule);

	FieldRule insert(FieldRule fieldRule);

	Boolean deleteByGroup(Integer group);
}

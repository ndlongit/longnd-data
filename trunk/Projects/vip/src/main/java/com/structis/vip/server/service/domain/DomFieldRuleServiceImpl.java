package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.FieldRule;
import com.structis.vip.server.dao.FieldRuleDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domFieldRuleService")
public class DomFieldRuleServiceImpl extends GenericEntityServiceImpl<FieldRule, Integer>
		implements DomFieldRuleService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(DomFieldRuleServiceImpl.class);

	@Autowired
	@Qualifier("fieldRuleDao")
	private FieldRuleDao fieldRuleDao;
	
	
	@Override
	public GenericDao<FieldRule, Integer> getDao() {
		return fieldRuleDao;
	}

	@Override
	public FieldRule getNew() {
		return new FieldRule();
	}

	@Override
	public FieldRule getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<FieldRule> getRulesByDemGroup(Integer group) {		
		return fieldRuleDao.getRulesByDemGroup(group);
	}

	@Override
	public List<FieldRule> getRules(String entId, String ptyId, Integer dnaId, Integer langId) {
		return fieldRuleDao.getRules( entId, ptyId, dnaId, langId);
	}

	@Transactional
	public Boolean update(FieldRule fieldRule) {
		fieldRuleDao.update(fieldRule);
		return true;
	}

	@Override
	public FieldRule insert(FieldRule fieldRule) {
		return fieldRuleDao.insert(fieldRule);
	}
	
	@Override
	public Boolean deleteByGroup(Integer group) {
		return fieldRuleDao.deleteByGroup(group);
	}
}

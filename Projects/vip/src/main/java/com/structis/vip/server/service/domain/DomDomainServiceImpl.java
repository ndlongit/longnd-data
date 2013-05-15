package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Domain;
import com.structis.vip.server.dao.DomainDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDomainService")
public class DomDomainServiceImpl extends GenericEntityServiceImpl<Domain, Integer> implements DomDomainService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomDomainServiceImpl.class);

	@Autowired
	@Qualifier("domainDao")
	private DomainDao domainDao;

	@Override
	public GenericDao<Domain, Integer> getDao() {
		return domainDao;
	}

	@Override
	public Domain getNew() {
		return new Domain();
	}

	@Override
	public Domain getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<Domain> getDomains() {
		return this.find();
	}
}
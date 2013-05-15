package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.dao.ExternControllerControlDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domExternControllerControlService")
public class DomExternControllerControlServiceImpl extends GenericEntityServiceImpl<ExtControllerControl, Integer> implements
		DomExternControllerControlService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomExternControllerControlServiceImpl.class);

	@Autowired
	@Qualifier("externControllerControlDao")
	private ExternControllerControlDao controlDao;

	@Override
	public GenericDao<ExtControllerControl, Integer> getDao() {
		return controlDao;
	}

	@Override
	public ExtControllerControl getNew() {
		return new ExtControllerControl();
	}

	@Override
	public ExtControllerControl getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public ExtControllerControl insert(ExtControllerControl nature) {
		return controlDao.insert(nature);
	}

	@Override
	public ExtControllerControl update(ExtControllerControl nature) {
		return controlDao.update(nature);
	}

	@Override
	public List<ExtControllerControl> findAll() {
		return this.find();
	}

	@Override
	public List<ExtControllerControl> findByControl(Integer id) {
		return controlDao.findByControl(id);
	}

	@Override
	public Boolean deleteByControl(Integer control) {
		return controlDao.deleteByControl(control);
	}	
}

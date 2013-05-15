package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Perimetre;
import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.dao.PerimetreDao;
import com.structis.vip.server.dao.PerimetreTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.PerimetreModel;

@Service("domPerimetreTypeService")
public class DomPerimetreTypeServiceImpl extends GenericEntityServiceImpl<PerimetreType, String>
		implements DomPerimetreTypeService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(DomPerimetreTypeServiceImpl.class);

	@Autowired
	@Qualifier("perimetreTypeDao")
	private PerimetreTypeDao perimetreTypeDao;
	

	@Override
	public GenericDao<PerimetreType, String> getDao() {
		return perimetreTypeDao;
	}

	@Override
	public PerimetreType getNew() {
		return new PerimetreType();
	}

	@Override
	public PerimetreType getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<PerimetreType> getPerimetreTypes(String entiteId) {		
		return perimetreTypeDao.getPerimetreTypes(entiteId);
	}

	@Override
	public PerimetreType insert(PerimetreType doc) {		
		return perimetreTypeDao.insert(doc);
	}

	@Override
	public PerimetreType update(PerimetreType doc) {
		return perimetreTypeDao.update(doc);
	}
}

package com.structis.vip.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.dao.DomDelDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDomDelService")
public class DomDomDelServiceImpl extends GenericEntityServiceImpl<DomDel, Integer> implements DomDomDelService {

	@Autowired
	@Qualifier("domDelDao")
	private DomDelDao domDelDao;

	@Override
	public GenericDao<DomDel, Integer> getDao() {
		return domDelDao;
	}

	@Override
	public DomDel getNew() {
		return null;
	}

	@Override
	public DomDel getNewWithDefaults() {
		return null;
	}

	@Override
	public List<DomDel> getByDelId(Integer delId) {
		return domDelDao.getByDelId(delId);
	}

	@Override
	public DomDel insert(DomDel demDom) {
		return domDelDao.insert(demDom);
	}

	@Override
	public Boolean deleteByDelId(Integer delId, String path) {
		return domDelDao.deleteByDelId(delId, path);
	}

	@Override
	public List<DomDel> findByDocumentModel(Integer dId) {
		return domDelDao.findByDocumentModel(dId);
	}
}

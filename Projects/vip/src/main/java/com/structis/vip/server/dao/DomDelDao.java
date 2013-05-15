package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.dao.support.GenericDao;

public interface DomDelDao extends GenericDao<DomDel, Integer> {

	List<DomDel> getByDelId(Integer delId);

	DomDel insert(DomDel demDom);

	Boolean deleteByDelId(Integer delId, String path);

	List<DomDel> findByDocumentModel(Integer dId);
}

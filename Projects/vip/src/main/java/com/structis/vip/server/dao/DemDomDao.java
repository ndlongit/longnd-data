package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.dao.support.GenericDao;

public interface DemDomDao extends GenericDao<DemDom, Integer> {
	List<DemDom> getAllDemDomsByDemGroup(Integer group);
	
	DemDom insert(DemDom demDom);
	
	Boolean deleteByGroup(Integer group);
	
	List<DemDom> findByDocumentModel(Integer dId);
}

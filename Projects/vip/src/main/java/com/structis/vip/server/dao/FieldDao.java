package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.dao.support.GenericDao;

public interface FieldDao extends GenericDao<FieField, Integer> {

	List<FieField> getRulesByEntiteId(String entiteId);

	FieField insert(FieField FieField);

	List<FieField> getFieldsByGroupName(String entId, String groupName);

	List<FieField> findByLanguageId(Integer languageId);

	List<FieField> findByEntite(String entId);
	
}

package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.ExternController;
import com.structis.vip.server.bean.domain.core.business.KeyValueVM;
import com.structis.vip.server.dao.support.GenericDao;

public interface ExternControllerDao extends GenericDao<ExternController, Integer> {
	public ExternController insert(ExternController nature);

	public ExternController update(ExternController nature);

	public List<KeyValueVM> getDelegatairesKeyValueByEntiteId(String entiteId);	

}

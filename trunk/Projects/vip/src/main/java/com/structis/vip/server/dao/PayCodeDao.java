package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.dao.support.GenericDao;

public interface PayCodeDao extends GenericDao<PayCode, Integer> {

	public PayCode findByCode(String code);

	public PayCode insert(PayCode nature);

	public PayCode update(PayCode nature);
}

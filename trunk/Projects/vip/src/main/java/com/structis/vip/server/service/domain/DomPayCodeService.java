package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomPayCodeService extends GenericEntityService<PayCode, Integer> {

    PayCode insert(PayCode doc);

    PayCode update(PayCode doc);

    List<PayCode> findAll();

    PayCode findByCode(String code);
}

package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.LotType;
import com.structis.fichesst.server.dao.LotTypeDao;

@Service
public class LotTypeServiceImpl extends BasicServiceImpl<LotType, Integer, LotTypeDao> implements LotTypeService {
}

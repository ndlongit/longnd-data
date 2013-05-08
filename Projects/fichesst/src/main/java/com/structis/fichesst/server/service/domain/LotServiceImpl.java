package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Lot;
import com.structis.fichesst.server.dao.LotDao;

@Service
public class LotServiceImpl extends BasicServiceImpl<Lot, Integer, LotDao> implements LotService {
}

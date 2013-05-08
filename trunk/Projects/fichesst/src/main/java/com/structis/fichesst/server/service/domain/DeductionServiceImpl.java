package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Deduction;
import com.structis.fichesst.server.dao.DeductionDao;

@Service
public class DeductionServiceImpl extends BasicServiceImpl<Deduction, Integer, DeductionDao> implements DeductionService {
}

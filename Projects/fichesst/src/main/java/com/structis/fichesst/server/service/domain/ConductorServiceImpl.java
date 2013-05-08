package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Conductor;
import com.structis.fichesst.server.dao.ConductorDao;

@Service
public class ConductorServiceImpl extends BasicServiceImpl<Conductor, Integer, ConductorDao> implements ConductorService {
}

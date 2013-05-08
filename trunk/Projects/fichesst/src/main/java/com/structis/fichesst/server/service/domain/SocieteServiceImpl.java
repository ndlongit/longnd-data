package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Societe;
import com.structis.fichesst.server.dao.SocieteDao;

@Service
public class SocieteServiceImpl extends BasicServiceImpl<Societe, Integer, SocieteDao> implements SocieteService {
}

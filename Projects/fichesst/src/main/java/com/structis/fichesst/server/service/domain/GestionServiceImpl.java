package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.server.dao.GestionDao;

@Service
public class GestionServiceImpl extends BasicServiceImpl<Gestion, Integer, GestionDao> implements GestionService {

}

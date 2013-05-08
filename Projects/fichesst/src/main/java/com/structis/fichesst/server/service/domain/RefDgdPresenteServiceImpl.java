package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.RefDgdPresente;
import com.structis.fichesst.server.dao.RefDgdPresenteDao;

@Service
public class RefDgdPresenteServiceImpl extends BasicServiceImpl<RefDgdPresente, Integer, RefDgdPresenteDao> implements
		RefDgdPresenteService {
}

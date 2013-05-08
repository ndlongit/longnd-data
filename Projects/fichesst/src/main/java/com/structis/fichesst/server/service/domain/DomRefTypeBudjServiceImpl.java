package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.RefTypeBudjConf;
import com.structis.fichesst.server.dao.RefTypeBudjDao;
@Service("domRefTypeBudjService")
public class DomRefTypeBudjServiceImpl extends BasicServiceImpl<RefTypeBudjConf, Integer, RefTypeBudjDao> implements DomRefTypeBudjService{

}

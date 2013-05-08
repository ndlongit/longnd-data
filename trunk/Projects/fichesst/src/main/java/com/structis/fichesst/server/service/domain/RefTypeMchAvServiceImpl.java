package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.RefTypeMchAv;
import com.structis.fichesst.server.dao.RefTypeMchAvDao;

@Service("refTypeMchAvService")
public class RefTypeMchAvServiceImpl extends BasicServiceImpl<RefTypeMchAv, Integer, RefTypeMchAvDao> implements
		RefTypeMchAvService {
}

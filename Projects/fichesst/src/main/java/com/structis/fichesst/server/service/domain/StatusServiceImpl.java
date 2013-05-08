package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Status;
import com.structis.fichesst.server.dao.StatusDao;

@Service
public class StatusServiceImpl extends BasicServiceImpl<Status, Integer, StatusDao> implements StatusService {
}

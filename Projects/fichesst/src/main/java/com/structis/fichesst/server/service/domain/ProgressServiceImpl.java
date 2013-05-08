package com.structis.fichesst.server.service.domain;

import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Progress;
import com.structis.fichesst.server.dao.ProgressDao;

@Service
public class ProgressServiceImpl extends BasicServiceImpl<Progress, Integer, ProgressDao> implements ProgressService {
}

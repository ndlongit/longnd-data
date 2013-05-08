package com.structis.fichesst.server.dao;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Progress;

@Repository
public class ProgressDaoImpl extends BasicDaoImpl<Progress, Integer> implements ProgressDao {
}

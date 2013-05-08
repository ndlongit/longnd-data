package com.structis.fichesst.server.dao;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Status;

@Repository
public class StatusDaoImpl extends BasicDaoImpl<Status, Integer> implements StatusDao {
}

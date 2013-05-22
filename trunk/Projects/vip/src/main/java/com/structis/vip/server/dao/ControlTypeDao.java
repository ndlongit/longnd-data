package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.ControlType;
import com.structis.vip.server.dao.support.GenericDao;

public interface ControlTypeDao extends GenericDao<ControlType, Integer> {

    public List<ControlType> findByEntite(String entiteId);

    public ControlType insert(ControlType nature);

    public ControlType update(ControlType nature);
}

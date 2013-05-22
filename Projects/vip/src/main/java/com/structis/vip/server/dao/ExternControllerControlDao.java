package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.dao.support.GenericDao;

public interface ExternControllerControlDao extends GenericDao<ExtControllerControl, Integer> {

    public ExtControllerControl insert(ExtControllerControl nature);

    public ExtControllerControl update(ExtControllerControl nature);

    public List<ExtControllerControl> findByControl(Integer id);

    public Boolean deleteByControl(Integer control);

}

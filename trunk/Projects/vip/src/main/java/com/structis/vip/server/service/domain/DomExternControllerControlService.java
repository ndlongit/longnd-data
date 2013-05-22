package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.ExtControllerControl;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomExternControllerControlService extends GenericEntityService<ExtControllerControl, Integer> {

    ExtControllerControl insert(ExtControllerControl doc);

    ExtControllerControl update(ExtControllerControl doc);

    List<ExtControllerControl> findAll();

    List<ExtControllerControl> findByControl(Integer id);

    Boolean deleteByControl(Integer control);
}

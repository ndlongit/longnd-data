package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.ControlTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientControlTypeService")
public interface ClientControlTypeService extends RemoteService {

    List<ControlTypeModel> findAll();

    List<ControlTypeModel> findByEntite(String entiteId);

    Boolean delete(ControlTypeModel model);

    ControlTypeModel insert(ControlTypeModel model);

    ControlTypeModel update(ControlTypeModel model);
}

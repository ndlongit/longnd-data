package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.model.ChantierTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientChantierTypeService")
public interface ClientChantierTypeService extends RemoteService {

    List<ChantierTypeModel> findAll();

    List<ChantierTypeModel> findChantierByEntite(String entiteId);

    Boolean delete(ChantierTypeModel model) throws ChantierTypeException;

    ChantierTypeModel insert(ChantierTypeModel model);

    ChantierTypeModel update(ChantierTypeModel model);
}

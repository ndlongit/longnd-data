package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.PerimetreTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientPerimetreTypeService")
public interface ClientPerimetreTypeService extends RemoteService {

    List<PerimetreTypeModel> getPerimetreTypes(String entiteId);

    Boolean delete(PerimetreTypeModel model);

    PerimetreTypeModel insert(PerimetreTypeModel model);

    PerimetreTypeModel update(PerimetreTypeModel model);
}

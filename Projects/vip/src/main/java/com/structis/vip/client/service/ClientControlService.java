package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.ControlFilter;
import com.structis.vip.shared.model.ControlModel;

@RemoteServiceRelativePath("springGwtServices/clientControlService")
public interface ClientControlService extends RemoteService {

    List<ControlModel> findAll();

    Boolean delete(ControlModel model);

    ControlModel insert(ControlModel model);

    ControlModel update(ControlModel model);

    PagingLoadResult<ControlModel> getControlsWithPaging(ControlFilter newFilter);

    List<ControlModel> getControls(ControlFilter config);

    List<ControlModel> findByPerimetre(String perId);
}

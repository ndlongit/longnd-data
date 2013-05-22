package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.DelegationTypeException;
import com.structis.vip.shared.model.DelegationTypeModel;

@RemoteServiceRelativePath("springGwtServices/clientDelegationTypeService")
public interface ClientDelegationTypeService extends RemoteService {

    List<DelegationTypeModel> getAllTypes();

    DelegationTypeModel getPrincipleType();

    DelegationTypeModel getTemporaryType();

    DelegationTypeModel getSubType();

    DelegationTypeModel getDelegationTypeOf(String type);

    DelegationTypeModel insert(DelegationTypeModel model) throws DelegationTypeException;

    DelegationTypeModel update(DelegationTypeModel model);
}

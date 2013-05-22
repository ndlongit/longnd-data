package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.PayCodeModel;

@RemoteServiceRelativePath("springGwtServices/clientPayCodeService")
public interface ClientPayCodeService extends RemoteService {

    List<PayCodeModel> findAll();

    PayCodeModel findByCode(String code);

    Boolean delete(PayCodeModel model);

    PayCodeModel insert(PayCodeModel model);

    PayCodeModel update(PayCodeModel model);
}

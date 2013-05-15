package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.DomainModel;

@RemoteServiceRelativePath("springGwtServices/clientDomainService")
public interface ClientDomainService extends RemoteService {
	List<DomainModel> getDomains();
}

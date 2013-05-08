package com.structis.fichesst.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/clientPropertiesService")
public interface ClientPropertiesService extends RemoteService {

	String getVersionInfo();
}

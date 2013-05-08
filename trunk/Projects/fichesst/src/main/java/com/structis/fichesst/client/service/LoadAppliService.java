package com.structis.fichesst.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.config.ApplicationContext;
@RemoteServiceRelativePath("springGwtServices/loadappli")
public interface LoadAppliService extends RemoteService {
	ApplicationContext loadappli();
}

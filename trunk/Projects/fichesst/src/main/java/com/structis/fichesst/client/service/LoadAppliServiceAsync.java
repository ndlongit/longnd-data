package com.structis.fichesst.client.service;

import com.structis.fichesst.shared.config.ApplicationContext;
import com.google.gwt.user.client.rpc.AsyncCallback;
public interface LoadAppliServiceAsync {
	void loadappli(AsyncCallback<ApplicationContext> callback);
}

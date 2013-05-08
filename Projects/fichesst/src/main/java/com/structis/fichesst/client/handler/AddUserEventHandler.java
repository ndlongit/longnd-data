package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.AddUserEvent;

public interface AddUserEventHandler extends EventHandler {
	public void onLoad(AddUserEvent event);
}

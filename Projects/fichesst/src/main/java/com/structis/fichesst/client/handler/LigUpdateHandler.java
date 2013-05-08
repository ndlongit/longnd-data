package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.LigUpdateEvent;

public interface LigUpdateHandler extends EventHandler{
	void onUpdate(LigUpdateEvent event);
}

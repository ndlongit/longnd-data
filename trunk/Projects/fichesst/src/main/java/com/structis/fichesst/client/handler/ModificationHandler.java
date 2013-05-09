package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ModificationEvent;

public interface ModificationHandler extends EventHandler {
	public void onload(ModificationEvent event);
}

package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.GestionGridUpdateEvent;

public interface GestionGridUpdateHandler extends EventHandler {
	void onSave(GestionGridUpdateEvent event);
}

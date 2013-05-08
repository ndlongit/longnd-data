package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.SocieteEvent;

public interface SocieteHandler extends EventHandler {
	public void onChangeSociete(SocieteEvent societeEvent);
}

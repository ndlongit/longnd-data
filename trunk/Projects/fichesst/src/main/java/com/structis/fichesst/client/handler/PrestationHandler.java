package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.PrestationEvent;

public interface PrestationHandler extends EventHandler {
	public void onChangePrestation(PrestationEvent prestationEvent);
}

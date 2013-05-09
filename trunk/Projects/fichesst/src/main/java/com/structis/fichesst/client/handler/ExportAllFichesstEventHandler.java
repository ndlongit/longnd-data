package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportAllFichesstEvent;

public interface ExportAllFichesstEventHandler extends EventHandler {
	public void onloadEvent(ExportAllFichesstEvent event);
}

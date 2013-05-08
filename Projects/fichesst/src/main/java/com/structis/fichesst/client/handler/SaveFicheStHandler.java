package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.SaveFicheStEvent;

public interface SaveFicheStHandler extends EventHandler {
	void onSave(SaveFicheStEvent event);
}

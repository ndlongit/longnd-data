package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportFicheStEvent;

public interface ExportFicheStHandler extends EventHandler {
	void onExport(ExportFicheStEvent event);
}

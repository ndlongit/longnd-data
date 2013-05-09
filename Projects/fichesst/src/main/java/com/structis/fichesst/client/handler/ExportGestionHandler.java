package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportGestionEvent;

public interface ExportGestionHandler extends EventHandler {
	void onExport(ExportGestionEvent event);
}

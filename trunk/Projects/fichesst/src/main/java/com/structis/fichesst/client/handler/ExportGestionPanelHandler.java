package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportGestionPanelEvent;

public interface ExportGestionPanelHandler extends EventHandler {
	void onExport(ExportGestionPanelEvent event);
}

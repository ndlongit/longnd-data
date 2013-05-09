package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesEvent;

public interface ExportSuiviDesAccomptesHandler extends EventHandler {
	void onExport(ExportSuiviDesAccomptesEvent event);
}

package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportSyntheseEvent;

public interface ExportSyntheseHandler extends EventHandler {
	void onExportSyntheseEcran(ExportSyntheseEvent event);
}

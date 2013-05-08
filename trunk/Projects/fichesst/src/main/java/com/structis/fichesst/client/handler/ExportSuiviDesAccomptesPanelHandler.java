package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesPanelEvent;

public interface ExportSuiviDesAccomptesPanelHandler extends EventHandler {
	void onExport(ExportSuiviDesAccomptesPanelEvent event);
}

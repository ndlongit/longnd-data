package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportAvancementPanelEvent;

public interface ExportAvancementPanelHandler extends EventHandler {
	void onExport(ExportAvancementPanelEvent event);
}

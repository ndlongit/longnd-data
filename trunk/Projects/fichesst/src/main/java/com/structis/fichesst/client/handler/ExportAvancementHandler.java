package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ExportAvancementEvent;

public interface ExportAvancementHandler extends EventHandler {
	void onExport(ExportAvancementEvent event);
}

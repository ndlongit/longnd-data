package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;

public interface DeductionGridUpdateHandler extends EventHandler {
	void onSave(DeductionGridUpdateEvent event);
}

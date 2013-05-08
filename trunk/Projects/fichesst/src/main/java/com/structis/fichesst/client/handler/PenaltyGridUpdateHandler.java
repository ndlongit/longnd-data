package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.PenaltyGridUpdateEvent;

public interface PenaltyGridUpdateHandler extends EventHandler {
	void onSave(PenaltyGridUpdateEvent event);
}

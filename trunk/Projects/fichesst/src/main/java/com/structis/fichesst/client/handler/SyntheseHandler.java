package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.SyntheseEvent;

public interface SyntheseHandler extends EventHandler {

	void onEvent(SyntheseEvent event);

}

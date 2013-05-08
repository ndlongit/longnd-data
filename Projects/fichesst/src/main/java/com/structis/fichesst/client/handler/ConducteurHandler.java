package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.ConducteurEvent;

public interface ConducteurHandler extends EventHandler {
	public void onChangeConducteur(ConducteurEvent conducteurEvent);
}

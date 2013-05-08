package com.structis.fichesst.client.handler;

import com.google.gwt.event.shared.EventHandler;
import com.structis.fichesst.client.event.EtatAvancementEvent;

public interface EtatAvancementHandler extends EventHandler {
	void onGetEtatAvancement(EtatAvancementEvent event);
}

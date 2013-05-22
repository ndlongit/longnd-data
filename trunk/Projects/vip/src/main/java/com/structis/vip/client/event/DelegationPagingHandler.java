package com.structis.vip.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DelegationPagingHandler extends EventHandler {

    void onLoadAction(DelegationPagingEvent event);
}

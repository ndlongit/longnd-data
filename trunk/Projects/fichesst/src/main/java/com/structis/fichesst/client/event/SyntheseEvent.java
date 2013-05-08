package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.SyntheseHandler;

public class SyntheseEvent extends GwtEvent<SyntheseHandler> {

	public static final Type<SyntheseHandler> TYPE = new Type<SyntheseHandler>();

	@Override
	public GwtEvent.Type<SyntheseHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SyntheseHandler handler) {
		handler.onEvent(this);
	}
}

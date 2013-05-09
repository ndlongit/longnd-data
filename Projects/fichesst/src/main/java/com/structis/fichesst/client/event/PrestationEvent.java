package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.PrestationHandler;

public class PrestationEvent extends GwtEvent<PrestationHandler> {

	public static final Type<PrestationHandler> TYPE = new Type<PrestationHandler>();

	public PrestationEvent() {
	}

	@Override
	public GwtEvent.Type<PrestationHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PrestationHandler handler) {
		handler.onChangePrestation(this);

	}
}

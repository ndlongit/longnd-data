package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportGestionHandler;

public class ExportGestionEvent extends GwtEvent<ExportGestionHandler> {
	public static final Type<ExportGestionHandler> TYPE = new Type<ExportGestionHandler>();

	@Override
	public GwtEvent.Type<ExportGestionHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportGestionHandler handler) {
		handler.onExport(this);
	}
}

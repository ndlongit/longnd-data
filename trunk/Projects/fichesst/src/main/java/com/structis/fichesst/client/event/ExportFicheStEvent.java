package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportFicheStHandler;

public class ExportFicheStEvent extends GwtEvent<ExportFicheStHandler> {

	public static final Type<ExportFicheStHandler> TYPE = new Type<ExportFicheStHandler>();

	@Override
	public GwtEvent.Type<ExportFicheStHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportFicheStHandler handler) {
		handler.onExport(this);
	}
}

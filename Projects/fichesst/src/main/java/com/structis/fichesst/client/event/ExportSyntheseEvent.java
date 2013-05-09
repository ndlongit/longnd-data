package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportSyntheseHandler;

public class ExportSyntheseEvent extends GwtEvent<ExportSyntheseHandler> {

	public static final Type<ExportSyntheseHandler> TYPE = new Type<ExportSyntheseHandler>();

	@Override
	public GwtEvent.Type<ExportSyntheseHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportSyntheseHandler handler) {
		handler.onExportSyntheseEcran(this);
	}
}

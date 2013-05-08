package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportSyntheseEcranHandler;

public class ExportSyntheseEcranEvent extends GwtEvent<ExportSyntheseEcranHandler> {

	public static final Type<ExportSyntheseEcranHandler> TYPE = new Type<ExportSyntheseEcranHandler>();

	@Override
	public GwtEvent.Type<ExportSyntheseEcranHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportSyntheseEcranHandler handler) {
		handler.onExportSyntheseEcran(this);
	}
}

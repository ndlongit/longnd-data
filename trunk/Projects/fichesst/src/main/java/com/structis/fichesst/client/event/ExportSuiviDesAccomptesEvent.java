package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportSuiviDesAccomptesHandler;

public class ExportSuiviDesAccomptesEvent extends GwtEvent<ExportSuiviDesAccomptesHandler> {

	public static final Type<ExportSuiviDesAccomptesHandler> TYPE = new Type<ExportSuiviDesAccomptesHandler>();

	public ExportSuiviDesAccomptesEvent() {
	}

	public static Type<ExportSuiviDesAccomptesHandler> getType() {
		return TYPE;
	}

	@Override
	public GwtEvent.Type<ExportSuiviDesAccomptesHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportSuiviDesAccomptesHandler handler) {
		handler.onExport(this);
	}
}
package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportGestionPanelHandler;

public class ExportGestionPanelEvent extends GwtEvent<ExportGestionPanelHandler> {
	public static final Type<ExportGestionPanelHandler> TYPE = new Type<ExportGestionPanelHandler>();

	@Override
	public GwtEvent.Type<ExportGestionPanelHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportGestionPanelHandler handler) {
		handler.onExport(this);
	}
}

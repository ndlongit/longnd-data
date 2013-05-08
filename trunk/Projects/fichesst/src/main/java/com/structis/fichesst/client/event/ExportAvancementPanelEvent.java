package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportAvancementPanelHandler;

public class ExportAvancementPanelEvent extends GwtEvent<ExportAvancementPanelHandler> {
	public static final Type<ExportAvancementPanelHandler> TYPE = new Type<ExportAvancementPanelHandler>();

	@Override
	public GwtEvent.Type<ExportAvancementPanelHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportAvancementPanelHandler handler) {
		handler.onExport(this);
	}
}

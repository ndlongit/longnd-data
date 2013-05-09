package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportAvancementHandler;

public class ExportAvancementEvent extends GwtEvent<ExportAvancementHandler> {
	public static final Type<ExportAvancementHandler> TYPE = new Type<ExportAvancementHandler>();

	public static Type<ExportAvancementHandler> getType() {
		return TYPE;
	}

	public ExportAvancementEvent() {

	}

	@Override
	public GwtEvent.Type<ExportAvancementHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportAvancementHandler handler) {
		handler.onExport(this);
	}
}

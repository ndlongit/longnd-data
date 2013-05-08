package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportSuiviDesAccomptesPanelHandler;

public class ExportSuiviDesAccomptesPanelEvent extends GwtEvent<ExportSuiviDesAccomptesPanelHandler> {

	public static final Type<ExportSuiviDesAccomptesPanelHandler> TYPE = new Type<ExportSuiviDesAccomptesPanelHandler>();

	@Override
	public GwtEvent.Type<ExportSuiviDesAccomptesPanelHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportSuiviDesAccomptesPanelHandler handler) {
		handler.onExport(this);
	}
}
package com.structis.vip.client.event.document;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DocumentModel;

public class LoadDocEvent extends GwtEvent<LoadDocHandler> {
	public static final int MODE_VIEW = 1;
	
	private static Type<LoadDocHandler> TYPE = new Type<LoadDocHandler>();
	
	private DocumentModel model;
	private int mode;

	public static Type<LoadDocHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadDocHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadDocHandler handler) {
		handler.onLoadAction(this);
	}

	public DocumentModel getModel() {
		return model;
	}

	public void setModel(DocumentModel model) {
		this.model = model;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
}

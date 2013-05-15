package com.structis.vip.client.event.document;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DocumentModel;

public class ModifyDocEvent extends GwtEvent<ModifyDocHandler> {
	public static final int MODE_VIEW = 1;
	
	private static Type<ModifyDocHandler> TYPE = new Type<ModifyDocHandler>();
	
	private DocumentModel model;
	private int mode;

	public static Type<ModifyDocHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyDocHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyDocHandler handler) {
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

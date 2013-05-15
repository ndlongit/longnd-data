package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DocumentMdlModel;

public class ModifyDocumentEvent extends GwtEvent<ModifyDocumentHandler> {
	private static Type<ModifyDocumentHandler> TYPE = new Type<ModifyDocumentHandler>();
	
	private DocumentMdlModel model;
	private int mode;

	public static Type<ModifyDocumentHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyDocumentHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyDocumentHandler handler) {
		handler.onLoadAction(this);
	}

	public DocumentMdlModel getModel() {
		return model;
	}

	public void setModel(DocumentMdlModel model) {
		this.model = model;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
}

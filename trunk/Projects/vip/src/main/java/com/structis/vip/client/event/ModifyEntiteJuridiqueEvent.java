package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

public class ModifyEntiteJuridiqueEvent extends
		GwtEvent<ModifyEntiteJuridiqueHandler> {
	private static Type<ModifyEntiteJuridiqueHandler> TYPE = new Type<ModifyEntiteJuridiqueHandler>();

	private EntiteJuridiqueModel model;

	private int mode;

	public static Type<ModifyEntiteJuridiqueHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyEntiteJuridiqueHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyEntiteJuridiqueHandler handler) {
		handler.onLoadAction(this);
	}

	public EntiteJuridiqueModel getModel() {
		return model;
	}

	public void setModel(EntiteJuridiqueModel model) {
		this.model = model;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
}

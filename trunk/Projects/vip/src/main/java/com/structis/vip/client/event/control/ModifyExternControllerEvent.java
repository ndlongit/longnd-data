package com.structis.vip.client.event.control;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ExternControllerModel;

public class ModifyExternControllerEvent extends GwtEvent<ModifyExternControllerHandler> {
	private static Type<ModifyExternControllerHandler> TYPE = new Type<ModifyExternControllerHandler>();
	
	private ExternControllerModel model;

	public static Type<ModifyExternControllerHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyExternControllerHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyExternControllerHandler handler) {
		handler.onLoadAction(this);
	}

	public ExternControllerModel getModel() {
		return model;
	}

	public void setModel(ExternControllerModel model) {
		this.model = model;
	}
}

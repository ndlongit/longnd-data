package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlTypeModel;

public class ModifyControlTypeEvent extends GwtEvent<ModifyControlTypeHandler> {
	private static Type<ModifyControlTypeHandler> TYPE = new Type<ModifyControlTypeHandler>();
	
	private ControlTypeModel model;

	public static Type<ModifyControlTypeHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyControlTypeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyControlTypeHandler handler) {
		handler.onLoadAction(this);
	}

	public ControlTypeModel getModel() {
		return model;
	}

	public void setModel(ControlTypeModel model) {
		this.model = model;
	}
}

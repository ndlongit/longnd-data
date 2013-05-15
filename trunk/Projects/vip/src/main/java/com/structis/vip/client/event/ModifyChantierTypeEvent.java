package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ChantierTypeModel;

public class ModifyChantierTypeEvent extends GwtEvent<ModifyChantierTypeHandler> {
	private static Type<ModifyChantierTypeHandler> TYPE = new Type<ModifyChantierTypeHandler>();
	
	private ChantierTypeModel model;

	public static Type<ModifyChantierTypeHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyChantierTypeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyChantierTypeHandler handler) {
		handler.onLoadAction(this);
	}

	public ChantierTypeModel getModel() {
		return model;
	}

	public void setModel(ChantierTypeModel model) {
		this.model = model;
	}
}

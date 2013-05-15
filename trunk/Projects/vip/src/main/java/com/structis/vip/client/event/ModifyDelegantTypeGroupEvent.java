package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public class ModifyDelegantTypeGroupEvent extends GwtEvent<ModifyDelegantTypeGroupHandler> {
	private static Type<ModifyDelegantTypeGroupHandler> TYPE = new Type<ModifyDelegantTypeGroupHandler>();
	
	private DelegantTypeGroupModel model;

	public static Type<ModifyDelegantTypeGroupHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyDelegantTypeGroupHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyDelegantTypeGroupHandler handler) {
		handler.onLoadAction(this);
	}

	public DelegantTypeGroupModel getModel() {
		return model;
	}

	public void setModel(DelegantTypeGroupModel model) {
		this.model = model;
	}
}

package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadUserEvent extends GwtEvent<LoadUserHandler> {
	private static Type<LoadUserHandler> TYPE = new Type<LoadUserHandler>();

	public static Type<LoadUserHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadUserHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadUserHandler handler) {
		handler.onLoadAction(this);
	}

}

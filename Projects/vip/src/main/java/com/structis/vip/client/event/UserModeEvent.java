package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserModeEvent extends GwtEvent<UserModeHandler> {

	private static Type<UserModeHandler> TYPE = new Type<UserModeHandler>();

	private String lblUserMode;

	public String getLblUserMode() {
		return lblUserMode;
	}

	public void setLblUserMode(String lblUserMode) {
		this.lblUserMode = lblUserMode;
	}

	public UserModeEvent() {
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UserModeHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<UserModeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UserModeHandler handler) {
		handler.onLoadAction(this);
	}

}

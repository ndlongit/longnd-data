package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AdministrationTreeEvent extends GwtEvent<AdministrationTreeHandler> {

	private static Type<AdministrationTreeHandler> TYPE = new Type<AdministrationTreeHandler>();

	private String parentId;
	private String perId;

	public static Type<AdministrationTreeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AdministrationTreeHandler handler) {
		handler.onLoadAction(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AdministrationTreeHandler> getAssociatedType() {
		return TYPE;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPerId() {
		return perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}
	
}

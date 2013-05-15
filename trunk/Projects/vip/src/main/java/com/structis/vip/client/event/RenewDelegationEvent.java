package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationModel;

public class RenewDelegationEvent extends GwtEvent<RenewDelegationHandler> {
	
	private static Type<RenewDelegationHandler> TYPE = new Type<RenewDelegationHandler>();
	
	private DelegationModel model;
	private int typeRenew  = 0; // 0: renew, 1: replace delegant/air
	
	public DelegationModel getModel() {
		return model;
	}

	public void setModel(DelegationModel model) {
		this.model = model;
	}

	public static Type<RenewDelegationHandler> getType(){
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RenewDelegationHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RenewDelegationHandler handler) {
		handler.onLoadAction(this);
	}

	public void setTypeRenew(int typeRenew) {
		this.typeRenew = typeRenew;
	}

	public int getTypeRenew() {
		return typeRenew;
	}

}
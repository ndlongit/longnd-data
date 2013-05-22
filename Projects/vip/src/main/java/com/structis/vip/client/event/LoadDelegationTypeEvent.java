package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadDelegationTypeEvent extends GwtEvent<LoadDelegationTypeHandler> {

    private static Type<LoadDelegationTypeHandler> TYPE = new Type<LoadDelegationTypeHandler>();

    public static Type<LoadDelegationTypeHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoadDelegationTypeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadDelegationTypeHandler handler) {
        handler.onLoadAction(this);
    }

}

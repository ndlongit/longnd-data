package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadGroupDelegationModelEvent extends GwtEvent<LoadGroupDelegationModelHandler> {

    private static Type<LoadGroupDelegationModelHandler> TYPE = new Type<LoadGroupDelegationModelHandler>();

    public static Type<LoadGroupDelegationModelHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoadGroupDelegationModelHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadGroupDelegationModelHandler handler) {
        handler.onLoadAction(this);
    }

}

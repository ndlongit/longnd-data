package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationModel;

public class DeleteDelegationEvent extends GwtEvent<DeleteDelegationHandler> {

    private static Type<DeleteDelegationHandler> TYPE = new Type<DeleteDelegationHandler>();

    private DelegationModel model;

    public DelegationModel getModel() {
        return this.model;
    }

    public void setModel(DelegationModel model) {
        this.model = model;
    }

    public static Type<DeleteDelegationHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DeleteDelegationHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteDelegationHandler handler) {
        handler.onLoadAction(this);
    }

}

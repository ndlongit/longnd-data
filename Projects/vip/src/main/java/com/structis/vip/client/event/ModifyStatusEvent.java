package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationStatusModel;

public class ModifyStatusEvent extends GwtEvent<ModifyStatusHandler> {

    private static Type<ModifyStatusHandler> TYPE = new Type<ModifyStatusHandler>();

    private DelegationStatusModel model;

    public static Type<ModifyStatusHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyStatusHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyStatusHandler handler) {
        handler.onLoadAction(this);
    }

    public DelegationStatusModel getModel() {
        return this.model;
    }

    public void setModel(DelegationStatusModel model) {
        this.model = model;
    }
}

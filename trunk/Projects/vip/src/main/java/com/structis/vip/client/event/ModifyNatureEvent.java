package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationNatureModel;

public class ModifyNatureEvent extends GwtEvent<ModifyNatureHandler> {

    private static Type<ModifyNatureHandler> TYPE = new Type<ModifyNatureHandler>();

    private DelegationNatureModel model;

    public static Type<ModifyNatureHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyNatureHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyNatureHandler handler) {
        handler.onLoadAction(this);
    }

    public DelegationNatureModel getModel() {
        return this.model;
    }

    public void setModel(DelegationNatureModel model) {
        this.model = model;
    }
}

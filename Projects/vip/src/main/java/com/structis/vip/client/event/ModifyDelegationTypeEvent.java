package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationTypeModel;

public class ModifyDelegationTypeEvent extends GwtEvent<ModifyDelegationTypeHandler> {

    private static Type<ModifyDelegationTypeHandler> TYPE = new Type<ModifyDelegationTypeHandler>();

    private DelegationTypeModel model;

    public static Type<ModifyDelegationTypeHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyDelegationTypeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyDelegationTypeHandler handler) {
        handler.onLoadAction(this);
    }

    public DelegationTypeModel getModel() {
        return this.model;
    }

    public void setModel(DelegationTypeModel model) {
        this.model = model;
    }
}

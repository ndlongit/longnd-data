package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ModifyPerimetreTypeEvent extends GwtEvent<ModifyPerimetreTypeHandler> {

    private static Type<ModifyPerimetreTypeHandler> TYPE = new Type<ModifyPerimetreTypeHandler>();

    private PerimetreTypeModel model;

    public static Type<ModifyPerimetreTypeHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyPerimetreTypeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyPerimetreTypeHandler handler) {
        handler.onLoadAction(this);
    }

    public PerimetreTypeModel getModel() {
        return this.model;
    }

    public void setModel(PerimetreTypeModel model) {
        this.model = model;
    }
}

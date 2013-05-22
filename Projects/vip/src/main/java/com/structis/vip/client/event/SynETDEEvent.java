package com.structis.vip.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.SynETDEModel;

public class SynETDEEvent extends GwtEvent<SynETDEHandler> {

    private static Type<SynETDEHandler> TYPE = new Type<SynETDEHandler>();

    private List<SynETDEModel> models;

    public static Type<SynETDEHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<SynETDEHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SynETDEHandler handler) {
        handler.onLoadAction(this);
    }

    public List<SynETDEModel> getModels() {
        return this.models;
    }

    public void setModels(List<SynETDEModel> models) {
        this.models = models;
    }

}

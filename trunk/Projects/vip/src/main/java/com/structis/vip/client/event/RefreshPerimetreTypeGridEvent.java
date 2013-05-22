package com.structis.vip.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class RefreshPerimetreTypeGridEvent extends GwtEvent<RefreshPerimetreTypeGridHandler> {

    private static Type<RefreshPerimetreTypeGridHandler> TYPE = new Type<RefreshPerimetreTypeGridHandler>();

    private List<PerimetreTypeModel> perimetreTypes;

    public RefreshPerimetreTypeGridEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RefreshPerimetreTypeGridHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<RefreshPerimetreTypeGridHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RefreshPerimetreTypeGridHandler handler) {
        handler.onLoadAction(this);
    }

    public List<PerimetreTypeModel> getPerimetreTypes() {
        return this.perimetreTypes;
    }

    public void setPerimetreTypes(List<PerimetreTypeModel> perimetreTypes) {
        this.perimetreTypes = perimetreTypes;
    }

}

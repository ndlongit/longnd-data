package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.client.navigation.Action;

public class LoadTabEvent extends GwtEvent<LoadTabHandler> {

    private static Type<LoadTabHandler> TYPE = new Type<LoadTabHandler>();
    private Action action;

    public LoadTabEvent(Action action) {
        this.action = action;
    }

    public static Type<LoadTabHandler> getType() {
        return TYPE;
    }

    public Action getAction() {
        return this.action;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoadTabHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadTabHandler handler) {
        handler.onLoadTab(this);

    }

}

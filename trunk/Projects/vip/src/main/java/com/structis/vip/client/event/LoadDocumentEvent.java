package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadDocumentEvent extends GwtEvent<LoadDocumentHandler> {

    private static Type<LoadDocumentHandler> TYPE = new Type<LoadDocumentHandler>();

    public static Type<LoadDocumentHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoadDocumentHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LoadDocumentHandler handler) {
        handler.onLoadAction(this);
    }

}

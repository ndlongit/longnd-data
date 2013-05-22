package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DelegationPagingEvent extends GwtEvent<DelegationPagingHandler> {

    private static Type<DelegationPagingHandler> TYPE = new Type<DelegationPagingHandler>();

    private int pageSize;

    public static Type<DelegationPagingHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DelegationPagingHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DelegationPagingHandler handler) {
        handler.onLoadAction(this);
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

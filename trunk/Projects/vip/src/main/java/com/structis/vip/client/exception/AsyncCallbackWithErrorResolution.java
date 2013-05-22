package com.structis.vip.client.exception;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackWithErrorResolution<T> implements AsyncCallback<T> {

    private ExceptionMessageMapper mapper = new ExceptionMessageMapper();

    @Override
    public void onFailure(Throwable caught) {
        this.mapper.map(caught);
    }
}

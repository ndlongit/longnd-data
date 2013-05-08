package com.structis.fichesst.client.exception;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackWithErrorResolution<T> implements AsyncCallback<T> {

	private ExceptionMessageMapper mapper = new ExceptionMessageMapper();

	public void onFailure(Throwable caught) {
		mapper.map(caught);
	}
}

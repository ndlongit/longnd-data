package com.structis.fichesst.client.exception;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallBackDecorator<T> implements AsyncCallback<T>, Serializable {

	private static final long serialVersionUID = 1L;

	public AsyncCallBackDecorator(AsyncCallback<T> asyncCallBack) {
		super();
		this.asyncCallBack = asyncCallBack;
	}

	private AsyncCallback<T> asyncCallBack;

	public void onFailure(Throwable caught) {
		asyncCallBack.onFailure(caught);
		afterFailure(caught);
	}

	public void onSuccess(T result) {
		beforeSucces(result);
		asyncCallBack.onSuccess(result);
		afterSucces(result);
	}

	public void beforeSucces(T result) {

	}

	public abstract void afterSucces(T result);

	public abstract void afterFailure(Throwable caught);
}

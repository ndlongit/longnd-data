package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.IsEditHandler;

public class IsEditEvent extends GwtEvent<IsEditHandler> {
	public static final Type<IsEditHandler> TYPE=new Type<IsEditHandler>();
	Boolean isEdit;
	public static Type<IsEditHandler> getType() {
		return TYPE;
	}
	public IsEditEvent(){
	}
	public IsEditEvent(Boolean isEdit){
		this.isEdit=isEdit;
	}
	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	@Override
	protected void dispatch(IsEditHandler arg0) {
		arg0.onload(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IsEditHandler> getAssociatedType() {
		return TYPE;
	}

}

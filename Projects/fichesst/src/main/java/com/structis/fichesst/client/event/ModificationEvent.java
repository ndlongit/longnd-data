package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ModificationHandler;

public class ModificationEvent extends GwtEvent<ModificationHandler> {
	public static final Type<ModificationHandler> TYPE=new Type<ModificationHandler>();
	Boolean isEdit;
	public static Type<ModificationHandler> getType() {
		return TYPE;
	}
	public ModificationEvent(){
	}
	public ModificationEvent(Boolean isEdit){
		this.isEdit=isEdit;
	}
	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	@Override
	protected void dispatch(ModificationHandler arg0) {
		arg0.onload(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModificationHandler> getAssociatedType() {
		return TYPE;
	}

}

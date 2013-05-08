package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.AddUserEventHandler;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class AddUserEvent extends GwtEvent<AddUserEventHandler>{
	public static final Type<AddUserEventHandler> TYPE=new Type<AddUserEventHandler>();
	public UtilisateurGrpModel user;
	public AddUserEvent(UtilisateurGrpModel user){
		this.user=user;
	}
	public UtilisateurGrpModel getUser() {
		return user;
	}
	public void setUser(UtilisateurGrpModel user) {
		this.user = user;
	}
	public static Type<AddUserEventHandler> getType() {
		return TYPE;
	}
	@Override
	protected void dispatch(AddUserEventHandler arg0) {
		arg0.onLoad(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddUserEventHandler> getAssociatedType() {
		return TYPE;
	}

}

package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.AddChantierEventHandler;
import com.structis.fichesst.shared.dto.ChantierModel;

public class AddChantierEvent extends GwtEvent<AddChantierEventHandler> {
	public static final Type<AddChantierEventHandler> TYPE=new Type<AddChantierEventHandler>();
	public ChantierModel chantierModel;
	public ChantierModel getChantierModel() {
		return chantierModel;
	}
	public AddChantierEvent(ChantierModel chantierModel){
		this.chantierModel=chantierModel;
	}
	public void setChantierModel(ChantierModel chantierModel) {
		this.chantierModel = chantierModel;
	}
	
	public static Type<AddChantierEventHandler> getType() {
		return TYPE;
	}
	@Override
	protected void dispatch(AddChantierEventHandler arg0) {
		arg0.onLoad(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AddChantierEventHandler> getAssociatedType() {
		return TYPE;
	}

}

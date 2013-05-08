package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.LigUpdateHandler;
import com.structis.fichesst.shared.dto.LigTransfertppModel;

public class LigUpdateEvent extends GwtEvent<LigUpdateHandler>{
	public static final Type<LigUpdateHandler> TYPE=new Type<LigUpdateHandler>();
	LigTransfertppModel ligModel;
	public LigUpdateEvent(LigTransfertppModel l){
		this.ligModel=l;
	}
	public LigTransfertppModel getLigModel() {
		return ligModel;
	}
	public void setLigModel(LigTransfertppModel ligModel) {
		this.ligModel = ligModel;
	}
	@Override
	protected void dispatch(LigUpdateHandler arg0) {
		arg0.onUpdate(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LigUpdateHandler> getAssociatedType() {
		return TYPE;
	}

}

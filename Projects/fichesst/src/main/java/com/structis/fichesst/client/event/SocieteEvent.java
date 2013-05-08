package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.SocieteHandler;

public class SocieteEvent extends GwtEvent<SocieteHandler>{

	public static final Type<SocieteHandler> TYPE = new Type<SocieteHandler>();
	
	private String societe;
	public SocieteEvent(String societe) {
		this.setSociete(societe);
	}
	@Override
	public GwtEvent.Type<SocieteHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SocieteHandler handler) {
		handler.onChangeSociete(this);
		
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getSociete() {
		return societe;
	}

}

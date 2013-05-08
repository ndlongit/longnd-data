package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ConducteurHandler;

public class ConducteurEvent extends GwtEvent<ConducteurHandler>{

	public static final Type<ConducteurHandler> TYPE = new Type<ConducteurHandler>();
	
	private String conducter;
	public ConducteurEvent(String conducter) {
		this.conducter = conducter;
	}
	@Override
	public GwtEvent.Type<ConducteurHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ConducteurHandler handler) {
		handler.onChangeConducteur(this);
		
	}

	public void setConducter(String conducter) {
		this.conducter = conducter;
	}

	public String getConducter() {
		return conducter;
	}

}

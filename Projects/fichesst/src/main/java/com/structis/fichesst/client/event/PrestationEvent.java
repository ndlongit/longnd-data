package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.PrestationHandler;

public class PrestationEvent extends GwtEvent<PrestationHandler>{

	public static final Type<PrestationHandler> TYPE = new Type<PrestationHandler>();
	
	private double value;
	private int index;
	public PrestationEvent(int index , double value) {
		this.index = index;
		this.value = value;
	}
	@Override
	public GwtEvent.Type<PrestationHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PrestationHandler handler) {
		handler.onChangePrestation(this);
		
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIndex() {
		return index;
	}

}

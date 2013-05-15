package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoadActionEvent extends GwtEvent<LoadActionHandler> {
	
	private static Type<LoadActionHandler> TYPE = new Type<LoadActionHandler>();
	
	private String produit;
	
	public LoadActionEvent(String produit){
		this.produit = produit;
	}
	
	public String getProduit(){
		return this.produit;
	}
	
	public static Type<LoadActionHandler> getType(){
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadActionHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadActionHandler handler) {
		handler.onLoadAction(this);
	}

}

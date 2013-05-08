package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.ecran.AbstractEcran;
import com.structis.fichesst.client.handler.SaveFicheStHandler;

public class SaveFicheStEvent extends GwtEvent<SaveFicheStHandler> {

	public static final Type<SaveFicheStHandler> TYPE = new Type<SaveFicheStHandler>();

	private AbstractEcran nextPage;

	@Override
	public GwtEvent.Type<SaveFicheStHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SaveFicheStHandler handler) {
		handler.onSave(this);
	}

	public AbstractEcran getNextPage() {
		return nextPage;
	}

	public void setNextPage(AbstractEcran nextPage) {
		this.nextPage = nextPage;
	}
}

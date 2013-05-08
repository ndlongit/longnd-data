package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.GestionGridUpdateHandler;
import com.structis.fichesst.shared.dto.GestionDto;

public class GestionGridUpdateEvent extends GwtEvent<GestionGridUpdateHandler> {

	public static final Type<GestionGridUpdateHandler> TYPE = new Type<GestionGridUpdateHandler>();

	private List<GestionDto> gestionDtoList;

	public GestionGridUpdateEvent(List<GestionDto> l) {
		this.gestionDtoList = l;
	}

	public List<GestionDto> getGestionDtoList() {
		return gestionDtoList;
	}

	public void setGestionDtoList(List<GestionDto> gestionDtoList) {
		this.gestionDtoList = gestionDtoList;
	}

	@Override
	public GwtEvent.Type<GestionGridUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GestionGridUpdateHandler handler) {
		handler.onSave(this);
	}
}

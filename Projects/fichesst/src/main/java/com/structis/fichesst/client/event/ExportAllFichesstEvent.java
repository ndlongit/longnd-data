package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportAllFichesstEventHandler;
import com.structis.fichesst.shared.dto.FicheStDto;

public class ExportAllFichesstEvent extends GwtEvent<ExportAllFichesstEventHandler> {
	public static final Type<ExportAllFichesstEventHandler> TYPE = new Type<ExportAllFichesstEventHandler>();
	List<FicheStDto> allFicheSt;

	public static Type<ExportAllFichesstEventHandler> getType() {
		return TYPE;
	}

	public List<FicheStDto> getAllFicheSt() {
		return allFicheSt;
	}

	public void setAllFicheSt(List<FicheStDto> allFicheSt) {
		this.allFicheSt = allFicheSt;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ExportAllFichesstEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportAllFichesstEventHandler handler) {
		handler.onloadEvent(this);
	}

}

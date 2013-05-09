package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.ExportAllFichestRefracEventHandler;
import com.structis.fichesst.shared.dto.FicheStDto;

public class ExportAllFicheStRefracEvent extends GwtEvent<ExportAllFichestRefracEventHandler> {

	public static final Type<ExportAllFichestRefracEventHandler> TYPE = new Type<ExportAllFichestRefracEventHandler>();
	List<FicheStDto> allFicheSt;

	public static Type<ExportAllFichestRefracEventHandler> getType() {
		return TYPE;
	}

	public List<FicheStDto> getAllFicheSt() {
		return allFicheSt;
	}

	public void setAllFicheSt(List<FicheStDto> allFicheSt) {
		this.allFicheSt = allFicheSt;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ExportAllFichestRefracEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ExportAllFichestRefracEventHandler handler) {
		handler.onloadEvent(this);
	}
}

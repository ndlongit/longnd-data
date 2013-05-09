package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.LoadFichestEcranHandler;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;

public class LoadFichestEcranEvent extends GwtEvent<LoadFichestEcranHandler> {
	public static final Type<LoadFichestEcranHandler> TYPE = new Type<LoadFichestEcranHandler>();
	private ChantierModel chantierModel;
	private FicheStDto fichestDto;

	public FicheStDto getFichestDto() {
		return fichestDto;
	}

	public void setFichestDto(FicheStDto fichestDto) {
		this.fichestDto = fichestDto;
	}

	public LoadFichestEcranEvent(ChantierModel chantierModel, FicheStDto fichestDto) {
		this.chantierModel = chantierModel;
		this.fichestDto = fichestDto;
	}

	public LoadFichestEcranEvent(ChantierModel chantierModel) {
		this.chantierModel = chantierModel;

	}

	@Override
	protected void dispatch(LoadFichestEcranHandler arg0) {
		arg0.onLoad(this);
	}

	public ChantierModel getChantierModel() {
		return chantierModel;
	}

	public void setChantierModel(ChantierModel chantierModel) {
		this.chantierModel = chantierModel;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadFichestEcranHandler> getAssociatedType() {
		return TYPE;
	}

}
